package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORGANIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddHealthWorkerCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.request.AddRequestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.healthworker.Organization;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.request.Request;
import seedu.address.model.request.RequestDate;
import seedu.address.model.request.RequestStatus;
import seedu.address.model.tag.Conditions;
import seedu.address.model.tag.Skills;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(String args) throws ParseException {
        CommandMode commandMode = ArgumentTokenizer.checkMode(args);
        if (commandMode == CommandMode.HEALTH_WORKER) {
            return parseAddHealthWorker(ArgumentTokenizer.trimMode(args));
        } else if (commandMode == CommandMode.REQUEST) {
            return parseAddRequest(args);
        }

        Person person = getPersonFromArgs(args);

        return new AddPersonCommand(person);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddRequestCommand
     * and returns an AddReqeustCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private AddRequestCommand parseAddRequest(String args) throws ParseException {
        UUID uuid = UUID.randomUUID();
        String requestId = uuid.toString();

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE,
            PREFIX_CONDITION);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_DATE, PREFIX_CONDITION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddRequestCommand.MESSAGE_USAGE));
        }

        RequestDate requestDate =
            ParserUtil.parseRequestDate(argumentMultimap.getValue(PREFIX_DATE).get());

        Conditions conditions = ParserUtil.parseConditions(argumentMultimap.getAllValues(PREFIX_CONDITION));

        return new AddRequestCommand(new Request(null, null, requestDate, conditions,
            new RequestStatus("PENDING")));

    }

    /**
     * @author Rohan
     * @param args argument list for add command
     * @return new AddPatientCommand for the adding of patient
     * with the fields specified in args
     * @throws ParseException if there are invalid/unfilled fields.
     */
    private AddPatientCommand parseAddPatient(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
            PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ORGANIZATION,
            PREFIX_NRIC, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SKILLS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC,
            PREFIX_ORGANIZATION, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_SKILLS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddPatientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Conditions conditions = ParserUtil.parseConditions(argMultimap.getAllValues(PREFIX_CONDITION));

        Patient patient = new Patient(name, phone, email, nric, address, tagList, conditions);

        return new AddPatientCommand(patient);
    }

    /**
     * @author Lookaz
     * Auxiliary method for parsing the adding of HealthWorker objects
     * @param args argument list for add command
     * @return new AddHealthWorkerCommand for the adding of health worker
     * with the fields specified in args
     * @throws ParseException if there are invalid/unfilled fields.
     * TODO: Handling of preamble before command mode
     */
    private AddHealthWorkerCommand parseAddHealthWorker(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ORGANIZATION,
                PREFIX_NRIC, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_SKILLS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC,
                PREFIX_ORGANIZATION, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_SKILLS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddHealthWorkerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Organization organization = ParserUtil.parseOrganization(argMultimap
                .getValue(PREFIX_ORGANIZATION).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Skills skills = ParserUtil.parseSpecialisations(argMultimap.getAllValues(PREFIX_SKILLS));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        HealthWorker healthWorker = new HealthWorker(name, phone, email,
                nric, address, tagList, organization, skills);

        return new AddHealthWorkerCommand(healthWorker);
    }

    /**
     * Extracts a Person object from the given object and returns it.
     * @throws ParseException if there are invalid/unfilled fields.
     */
    private Person getPersonFromArgs(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Person(name, phone, email, address, tagList);
    }

}
