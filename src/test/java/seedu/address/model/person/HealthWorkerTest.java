package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORGANIZATION_BETTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BETTY;
import static seedu.address.testutil.TypicalHealthWorkers.ANDY;
import static seedu.address.testutil.TypicalHealthWorkers.BETTY;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.HealthWorkerBuilder;

public class HealthWorkerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        HealthWorker healthWorker = new HealthWorkerBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        healthWorker.getTags().remove(0);
    }

    @Test
    public void isSameHealthWorker() {
        // same object -> returns true
        assertTrue(ANDY.isSameHealthWorker(ANDY));

        // null -> returns false
        assertFalse(ANDY.isSameHealthWorker(null));

        // different phone -> returns false
        HealthWorker editedAndy = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withPhone(VALID_PHONE_BETTY)).build();
        assertFalse(ANDY.isSameHealthWorker(editedAndy));

        // different NRIC -> returns false
        editedAndy = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withNric(VALID_NRIC_BETTY)).build();
        assertFalse(ANDY.isSameHealthWorker(editedAndy));

        // different name -> returns false
        editedAndy = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withName(VALID_NAME_BETTY)).build();
        assertFalse(ANDY.isSameHealthWorker(editedAndy));

        // same name, same phone, different organization -> returns false
        editedAndy = new HealthWorkerBuilder(ANDY)
                .withOrganization(VALID_ORGANIZATION_BETTY).build();
        assertFalse(ANDY.isSameHealthWorker(editedAndy));

        // same name, same phone, same organization, different email -> returns
        // true
        editedAndy = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY).withEmail
                (VALID_EMAIL_BETTY)).build();
        assertTrue(ANDY.isSameHealthWorker(editedAndy));
    }

    @Test
    public void equals() {
        // same values -> returns true
        HealthWorker editedAndy = new HealthWorkerBuilder(ANDY).build();
        assertTrue(ANDY.equals(editedAndy));

        // same object -> returns true
        assertTrue(ANDY.equals(ANDY));

        // null -> returns false
        assertFalse(ANDY.equals(null));

        // different type -> returns false
        assertFalse(ANDY.equals(5));

        // different person -> returns false
        assertFalse(ANDY.equals(BETTY));

        // different name -> returns false
        HealthWorker editedWorkerA = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withName(VALID_NAME_BETTY)).build();
        assertFalse(ANDY.equals(editedWorkerA));

        // different NRIC -> returns false
        editedWorkerA = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withNric(VALID_NRIC_BETTY)).build();
        assertFalse(ANDY.equals(editedWorkerA));

        // different phone -> returns false
        editedWorkerA = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withPhone(VALID_PHONE_BETTY)).build();
        assertFalse(ANDY.equals(editedWorkerA));

        // different email -> returns false
        editedWorkerA = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withEmail(VALID_EMAIL_BETTY)).build();
        assertFalse(ANDY.equals(editedWorkerA));

        // different address -> returns false
        editedWorkerA = ((HealthWorkerBuilder) new HealthWorkerBuilder(ANDY)
                .withAddress(VALID_ADDRESS_BETTY)).build();
        assertFalse(ANDY.equals(editedWorkerA));
    }
}
