package seedu.address.model.request;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.tag.Tag;

/**
 * Represents a request made by a patient in the request book.
 */
public class Request {

    private String id;
    private final Person patient;
    private final RequestDate requestDate;
    private final Set<Tag> conditions;
    private Optional<HealthWorker> healthWorker;
    private RequestStatus requestStatus;

    /**
     *
     * Minimally requires the following parameters to be non-null.
     *
     * @param id The id of the request
     * @param patient The patient requesting for services.
     * @param requestDate The date of the request.
     * @param conditions The set of the conditions the patient is requesting treatment for.
     * @param requestStatus The state of the request.
     */
    public Request(String id, Person patient, RequestDate requestDate, Set<Tag> conditions,
                   RequestStatus requestStatus) {
        requireAllNonNull(patient, requestDate, conditions, requestStatus);
        this.id = id;
        this.patient = patient;
        this.requestDate = requestDate;
        this.conditions = conditions;
        this.requestStatus = requestStatus;
        this.healthWorker = Optional.empty();
    }

    /*
     * Requires all the properties of a request to be non-null.
     */
    public Request(String id, Person patient, HealthWorker healthStaff, RequestDate requestDate, Set<Tag> conditions,
                   RequestStatus requestStatus) {
        this(id, patient, requestDate, conditions, requestStatus);
        requireNonNull(healthStaff);
        this.healthWorker = Optional.of(healthStaff);
    }

    /**
     * Overloaded constructor that takes in differing arguments for the patient.
     */
    public Request(Name name, Phone phone, Address address, RequestDate requestDate,
                   Set<Tag> conditions, RequestStatus status) {
        requireAllNonNull(name, phone, address, requestDate, conditions, status);
        this.patient = new Person(name, phone, address, conditions);
        this.conditions = conditions;
        this.requestStatus = status;
        this.requestDate = requestDate;
    }

    /**
     * Overloaded constructor that takes in a {@code healthWorker}.
     */
    public Request(Name name, Phone phone, Address address, RequestDate requestDate,
                   Set<Tag> conditions, RequestStatus status, HealthWorker healthWorker) {
        requireAllNonNull(name, phone, address, requestDate, conditions, status);
        this.patient = new Person(name, phone, address, conditions);
        this.conditions = conditions;
        this.requestStatus = status;
        this.requestDate = requestDate;
        this.healthWorker = Optional.ofNullable(healthWorker);
    }

    public void setHealthStaff(HealthWorker healthStaff) {
        requireNonNull(healthStaff);
        this.healthWorker = Optional.of(healthStaff);
    }

    /**
     * Returns true if both requests of the same ID and date have at least one other
     * property field that is the same.
     * This defines a weaker notion of equality between two requests.
     */
    public boolean isSameRequest(Request otherRequest) {
        if (otherRequest == this) {
            return true;
        }

        if (otherRequest == null) {
            return false;
        }

        if (this.id == null || otherRequest.id == null) {
            return otherRequest.getRequestDate().equals(this.requestDate)
                && ((otherRequest.getPatient().equals(this.patient)) || otherRequest.getConditions()
                .equals(this.conditions));
        }

        return otherRequest.getId().equals(this.id)
                && otherRequest.getRequestDate().equals(this.requestDate)
                && ((otherRequest.getPatient().equals(this.patient)) || otherRequest
                .getConditions().equals(this.conditions));
    }

    @Override
    public String toString() {

        String identifier = (id == null) ? "null" : this.id;
        String healthStaff = this.healthWorker.map(Person::toString)
                .orElse("Unassigned");

        return "----------Request----------\n"
                + "ID: " + identifier + "\n"
                + "Patient: " + this.patient + "\n"
                + "Assigned staff: " + healthStaff + "\n"
                + "Request Date: " + this.requestDate + "\n"
                + "Condition(s): " + this.conditions + "\n"
                + "Status: " + this.requestStatus + "\n"
                + "----------End of Request----------\n";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Request)) {
            return false;
        }

        Request otherRequest = (Request) other;

        if (otherRequest.getId() != null && this.id != null) {
            if (!getId().equals(this.id)) {
                return false;
            }
        }

        return (otherRequest.getRequestDate().equals(this.requestDate))
                && (otherRequest.getPatient().equals(this.patient))
                && (otherRequest.getConditions().equals(this.conditions))
                && otherRequest.getHealthStaff().equals(this.healthWorker)
                && (otherRequest.getRequestStatus().equals(this.requestStatus));
    }

    public Set<Tag> getConditions() {
        return this.conditions;
    }

    public RequestStatus getRequestStatus() {
        return this.requestStatus;
    }

    public String getId() {
        return this.id;
    }

    public Person getPatient() {
        return patient;
    }

    public RequestDate getRequestDate() {
        return this.requestDate;
    }

    public Optional<HealthWorker> getHealthStaff() {
        return healthWorker;
    }

}
