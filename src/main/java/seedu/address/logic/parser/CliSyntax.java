package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // Additional prefixes for HealthWorker and Patient class
    public static final Prefix PREFIX_ORGANIZATION = new Prefix("o/");
    public static final Prefix PREFIX_CONDITION = new Prefix("c/");
    public static final Prefix PREFIX_SKILLS = new Prefix("s/");

    // Prefixes for Add/Edit/Delete Command modes
    public static final Prefix PREFIX_ADD_HEALTHWORKER = new Prefix("1");
    public static final Prefix PREFIX_ADD_PATIENT = new Prefix("2");
    public static final Prefix PREFIX_ADD_OTHERS = new Prefix("3");

}
