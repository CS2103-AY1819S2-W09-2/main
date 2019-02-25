package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class OrganizationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Organization(null));
    }

    @Test
    public void constructor_invalidOrgName_throwsIllegalArgumentException() {
        String invalidOrgName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Name
                (invalidOrgName));
    }

    @Test
    public void isValidName() {
        // null orgname
        Assert.assertThrows(NullPointerException.class, () -> Organization
                .isValidOrgName(null));

        // invalid organization names
        assertFalse(Organization.isValidOrgName("")); // empty string
        assertFalse(Organization.isValidOrgName(" ")); // spaces only
        assertFalse(Organization.isValidOrgName("^")); // only non-alphanumeric characters
        assertFalse(Organization.isValidOrgName("peter*")); // contains non-alphanumeric
        // characters

        // valid name
        assertTrue(Organization.isValidOrgName("hello world"));
        //alphabets only
        assertTrue(Organization.isValidOrgName("12345")); // numbers only
        assertTrue(Organization.isValidOrgName("4tune cookie"));
        // alphanumeric characters
        assertTrue(Organization.isValidOrgName("Capital City")); //
        // with capital letters
        assertTrue(Organization.isValidOrgName("Za Warudo Over Heaven 2"));
        // long names
    }

}