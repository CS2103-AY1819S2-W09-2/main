package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Interface representing commands involving a Person
 */
public interface RequestCommand {

    String ADD_REQUEST_PARAMETERS =
        PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_NRIC
            + "NRIC " + PREFIX_ADDRESS + "ADDRESS " + "[" + PREFIX_CONDITION + "CONDITION]...";

    String ADD_REQUEST_EXAMPLE =
        PREFIX_NAME + "John Doe " + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example" +
            ".com " + PREFIX_NRIC + "S9621482G " + PREFIX_ADDRESS + "311, Clementi Ave 2, " +
            "#02-25" + PREFIX_CONDITION + "Palliative";

    String MESSAGE_DUPLICATE_REQUEST = "This request already exists in the request book";

}
