package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.parser.CommandMode;

/**
 * Interface representing commands involving a Person
 */
public interface RequestCommand {

    String ADD_REQUEST_PARAMETERS = AddCommand.COMMAND_WORD + " " + CommandMode.MODE_REQUEST + " "
        + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_NRIC
        + "NRIC " + PREFIX_ADDRESS + "ADDRESS " + "[" + PREFIX_CONDITION + "CONDITION]...";

    String ADD_REQUEST_EXAMPLE =
        AddCommand.COMMAND_WORD + " " + CommandMode.MODE_REQUEST + " " + "John Doe " + PREFIX_PHONE
            + "98765432 " + PREFIX_EMAIL + "johnd" + "@example" + ".com " + PREFIX_NRIC + "S9621482G "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, " + "#02-25" + PREFIX_CONDITION + "Palliative";

    String MESSAGE_DUPLICATE_REQUEST = "This request already exists in the request book";

}
