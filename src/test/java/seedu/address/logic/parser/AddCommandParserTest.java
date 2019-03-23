package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ORGANIZATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SKILLS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODE_HEALTHWORKER;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.ORGANIZATION_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.ORGANIZATION_DESC_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SKILLS_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORGANIZATION_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ANDY;
import static seedu.address.logic.parser.AddCommandParser.INVALID_COMMAND_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalHealthWorkers.ANDY;

import org.junit.Test;

import seedu.address.logic.commands.AddHealthWorkerCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.healthworker.Organization;
import seedu.address.model.tag.Specialisation;
import seedu.address.testutil.HealthWorkerBuilder;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_invalidCommandMode() {
        // missing command mode
        assertParseFailure(parser, NAME_DESC_ANDY + PHONE_DESC_ANDY + ORGANIZATION_DESC_ANDY + NRIC_DESC_ANDY
                + SKILLS_DESC_ANDY, String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_COMMAND_USAGE));

        // invalid command mode
        assertParseFailure(parser, INVALID_MODE + NAME_DESC_ANDY + PHONE_DESC_ANDY + ORGANIZATION_DESC_ANDY +
                NRIC_DESC_ANDY + SKILLS_DESC_ANDY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                INVALID_COMMAND_USAGE));
    }

    @Test
    public void parse_addHealthWorker_validFields() {
        HealthWorker expectedWorker = new HealthWorkerBuilder(ANDY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODE_HEALTHWORKER
                        + NAME_DESC_ANDY + PHONE_DESC_ANDY + ORGANIZATION_DESC_ANDY + NRIC_DESC_ANDY + SKILLS_DESC_ANDY,
                new AddHealthWorkerCommand(expectedWorker));

        // multiple names - last name accepted
        assertParseSuccess(parser, MODE_HEALTHWORKER + NAME_DESC_BETTY
                        + NAME_DESC_ANDY + PHONE_DESC_ANDY + ORGANIZATION_DESC_ANDY + NRIC_DESC_ANDY + SKILLS_DESC_ANDY,
                new AddHealthWorkerCommand(expectedWorker));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY
                        + PHONE_DESC_BETTY + PHONE_DESC_ANDY + ORGANIZATION_DESC_ANDY + NRIC_DESC_ANDY + SKILLS_DESC_ANDY,
                new AddHealthWorkerCommand(expectedWorker));

        // multiple NRIC - last NRIC accepted
        assertParseSuccess(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                        + ORGANIZATION_DESC_ANDY + NRIC_DESC_BETTY + NRIC_DESC_ANDY + SKILLS_DESC_ANDY,
                new AddHealthWorkerCommand(expectedWorker));

        // multiple organizations - last organization accepted
        assertParseSuccess(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                        + ORGANIZATION_DESC_BETTY + ORGANIZATION_DESC_ANDY + NRIC_DESC_ANDY + SKILLS_DESC_ANDY,
                new AddHealthWorkerCommand(expectedWorker));
    }

    @Test
    public void parse_addHealthWorker_missingFields() {
        // Missing prefix tests
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddHealthWorkerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, MODE_HEALTHWORKER + " " + VALID_NAME_ANDY + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + VALID_PHONE_ANDY
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, expectedMessage);

        // missing organization prefix
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + VALID_ORGANIZATION_ANDY + SKILLS_DESC_ANDY, expectedMessage);

        // missing nric prefix
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + VALID_NRIC_ANDY + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, expectedMessage);

        // missing skills prefix
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY, expectedMessage);
    }

    @Test
    public void parse_addHealthWorker_invalidFields() {
        // invalid name
        assertParseFailure(parser, MODE_HEALTHWORKER + INVALID_NAME_DESC + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + INVALID_PHONE_DESC
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, Phone.MESSAGE_CONSTRAINTS);

        // invalid nric
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + INVALID_NRIC_DESC + ORGANIZATION_DESC_ANDY + SKILLS_DESC_ANDY, Nric.MESSAGE_CONSTRAINTS);

        // invalid organization
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + INVALID_ORGANIZATION_DESC + SKILLS_DESC_ANDY, Organization.MESSAGE_CONSTRAINTS);

        // invalid skills
        assertParseFailure(parser, MODE_HEALTHWORKER + NAME_DESC_ANDY + PHONE_DESC_ANDY
                + NRIC_DESC_ANDY + ORGANIZATION_DESC_ANDY + INVALID_SKILLS_DESC, Specialisation.MESSAGE_CONSTRAINTS);

    }
}
