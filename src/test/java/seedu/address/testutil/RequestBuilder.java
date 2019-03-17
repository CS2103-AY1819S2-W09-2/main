package seedu.address.testutil;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.healthworker.Organization;
import seedu.address.model.request.Request;
import seedu.address.model.request.RequestDate;
import seedu.address.model.request.RequestStatus;
import seedu.address.model.tag.ConditionTag;
import seedu.address.model.tag.Conditions;

/**
 * A utility class to help with building Request objects.
 */
public class RequestBuilder {
    public static final String DEFAULT_ID = null;
    public static final String DEFAULT_PATIENT_NAME = "Amanda Tan";
    public static final String DEFAULT_PATIENT_PHONE = "85355255";
    public static final String DEFAULT_PATIENT_EMAIL = "amandatan@test.com";
    public static final String DEFAULT_PATIENT_NRIC = "S9670515H";
    public static final String DEFAULT_PATIENT_ADDRESS = "123, Far East Ave 3, #04-123, 123456";
    public static final String DEFAULT_STATUS = "PENDING";
    public static final String DEFAULT_DATE = "01-01-2019 08:00:00";
    public static final String DEFAULT_REQUEST = "Physiotherapy";
    public static final String DEFAULT_STAFF_NAME = "John Doe";
    public static final String DEFAULT_STAFF_PHONE = "81237822";
    public static final String DEFAULT_STAFF_ADDRESS = "Health Hub Ave 3, 129834";
    public static final String DEFAULT_STAFF_EMAIL = "healthstaff@health.com";
    public static final String DEFAULT_STAFF_NRIC = "S9123742I";
    public static final String DEFAULT_ORGANISATION = "NUH";

    private String id;
    private RequestDate requestDate;
    private RequestStatus requestStatus;
    private Conditions conditions;
    private Optional<HealthWorker> healthWorker;
    private Patient patient;

    public RequestBuilder() {
        this.id = DEFAULT_ID;
        this.patient = new Patient(new Name(DEFAULT_PATIENT_NAME), new Phone(DEFAULT_PATIENT_PHONE),
            new Email(DEFAULT_PATIENT_EMAIL), new Nric(DEFAULT_PATIENT_NRIC), new Address(DEFAULT_PATIENT_ADDRESS),
            Collections.emptySet(), new Conditions(new HashSet<>(Arrays.asList(new ConditionTag(DEFAULT_REQUEST)))));
        this.healthWorker = Optional.of(new HealthWorker(new Name(DEFAULT_STAFF_NAME), new Phone(DEFAULT_STAFF_PHONE),
                new Email(DEFAULT_STAFF_EMAIL), new Nric(DEFAULT_STAFF_NRIC), new Address(DEFAULT_STAFF_ADDRESS),
                Collections.emptySet(), new Organization(DEFAULT_ORGANISATION)));
        this.requestDate = new RequestDate(DEFAULT_DATE);
        this.requestStatus = new RequestStatus(DEFAULT_STATUS);
    }

    /*
     * Initializes the RequestBuilder with the data of {@code requestToCopy}.
     */
    public RequestBuilder(Request requestToCopy) {
        this.id = requestToCopy.getId();
        this.patient = requestToCopy.getPatient();
        this.healthWorker = requestToCopy.getHealthStaff();
        this.conditions = requestToCopy.getConditions();
        this.requestDate = requestToCopy.getRequestDate();
        this.requestStatus = requestToCopy.getRequestStatus();
    }

    /**
     * Sets the {@code id} of the {@code Request} that we are building.
     *
     * @param id The id of the request.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code requestDate} of the {@code Request} that we are building
     *
     * @param date the date of the request.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withDate(String date) {
        this.requestDate = new RequestDate(date);
        return this;
    }

    /**
     * Sets the {@code address} of the {@code patient} that we are building
     * @param address the address of the patient
     * @return The RequestBuilder object
     */
    public RequestBuilder withAddress(String address) {
        this.patient = new PatientBuilder(this.patient).withAddress(address).build();
        this.patient = new PatientBuilder(this.patient).withAddress(address).build();
        return this;
    }

    /**
     * Sets the {@code patient} of the {@code Request} we are building.
     *
     * @param patient The patient making the request.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Sets the {@code healthStaff} of the {@code Request} we are building.
     *
     * @param healthStaff The healthStaff attending to the request.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withHealthStaff(HealthWorker healthStaff) {
        requireNonNull(healthStaff);
        this.healthWorker = Optional.of(healthStaff);
        return this;
    }

    /**
     * Sets the {@code healthStaff} of the {@code Request} we are building.
     *
     * @param conditions The conditions that need be attended to, by the healthworker.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withConditions(Conditions conditions) {
        this.conditions = new Conditions(conditions);
        return this;
    }

    /**
     * Sets the {@code healthStaff} of the {@code Request} we are building.
     *
     * @param status The status of the request.
     * @return The RequestBuilder object.
     */
    public RequestBuilder withStatus(String status) {
        this.requestStatus = new RequestStatus(status);
        return this;
    }

    /**
     * Builds and returns the request.
     */
    public Request build() {
        return this.healthWorker.map(person -> new Request(this.id, this.patient, person, this.requestDate,
                this.conditions, this.requestStatus))
                .orElseGet(() -> new Request(this.id, this.patient, this.requestDate, this.conditions,
                        this.requestStatus));
    }

}
