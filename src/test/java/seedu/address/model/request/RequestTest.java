package seedu.address.model.request;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static seedu.address.testutil.TypicalHealthWorkers.BETTY;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalRequests.ALICE_REQUEST;
import static seedu.address.testutil.TypicalRequests.BENSON_REQUEST;

import org.junit.Test;

import seedu.address.testutil.RequestBuilder;

public class RequestTest {

    @Test
    public void isSameRequest() {
        // same object -> returns true
        assertTrue(ALICE_REQUEST.isSameRequest(ALICE_REQUEST));

        // null -> returns false
        assertFalse(ALICE_REQUEST.isSameRequest(null));

        // different id -> returns false
        Request editedAlice = new RequestBuilder(ALICE_REQUEST).withId("12").build();
        assertFalse(ALICE_REQUEST.isSameRequest(editedAlice));

        // everything same, but conditions different -> returns true
        editedAlice = new RequestBuilder(ALICE_REQUEST).withConditions("Stroke").build();
        assertTrue(ALICE_REQUEST.isSameRequest(editedAlice));

        // different date, everything else same -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withDate("03-10-2018 10:00:01").build();
        assertFalse(ALICE_REQUEST.isSameRequest(editedAlice));

        // same everything, different health staff -> returns true
        editedAlice = new RequestBuilder(ALICE_REQUEST)
                .withHealthStaff(BETTY).build();
        assertTrue(ALICE_REQUEST.isSameRequest(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Request aliceCopy = new RequestBuilder(ALICE_REQUEST).build();
        assertTrue(ALICE_REQUEST.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_REQUEST.equals(ALICE_REQUEST));

        // different type -> returns false
        assertFalse(ALICE_REQUEST.equals(10));

        // different type -> returns false
        assertFalse(ALICE_REQUEST.equals(BENSON_REQUEST));

        // different name -> returns false
        Request editedAlice = new RequestBuilder(ALICE_REQUEST).withPatient(BENSON).build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));

        // different date -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withDate("01-01-2019 10:00:23").build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));

        // different health staff -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withHealthStaff(BETTY).build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));

        // different treatment conditions -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withConditions("Cancer").build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));

        // different id -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withId("1").build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));

        // different isComplete status -> returns false
        editedAlice = new RequestBuilder(ALICE_REQUEST).withStatus(true).build();
        assertFalse(ALICE_REQUEST.equals(editedAlice));
    }
}
