package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
import seedu.address.model.person.HealthWorker;
//import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
//import seedu.address.model.person.Phone;
import seedu.address.model.request.RequestDate;
import seedu.address.model.request.RequestStatus;
import seedu.address.model.request.Request;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Request}.
 */
class JsonAdaptedRequest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Request's %s field is missing!";

    private final String id;
    private final String patient;
    private final String requestDate;
    private final List<JsonAdaptedTag> conditions = new ArrayList<>();
    private final String healthWorker;
    private final String requestStatus;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedRequest(@JsonProperty("id") String id,
                              @JsonProperty("patient") String patient,
                              @JsonProperty("requestdate") String requestDate,
                              @JsonProperty("healthstaff") String healthWorker,
                              @JsonProperty("conditions") List<JsonAdaptedTag> conditions,
                              @JsonProperty("requestStatus") String requestStatus) {
        this.id = id;
        this.patient = patient;
        this.requestDate = requestDate;
        if (conditions != null) {
            this.conditions.addAll(conditions);
        }
        this.healthWorker = healthWorker;
        this.requestStatus = requestStatus;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedRequest(Request source) {
        this.id = source.getId();
        this.patient = source.getPatient().getNric().toString();
        this.conditions.addAll(source.getConditions().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        this.requestDate = source.getRequestDate().toString();
        this.requestStatus = source.getRequestStatus().toString();
        this.healthWorker = source.getHealthStaff().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Request toModelType() throws IllegalValueException {
        final List<Tag> requestConditions = new ArrayList<>();
        for (JsonAdaptedTag tag : conditions) {
            requestConditions.add(tag.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }

        final String modelId = this.id;


        if (patient == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }

        final Person modelPatient = JsonSerializableAddressBook.obtainPersonFromHashmap(patient);
        final HealthWorker modelHealthStaff;
        if (healthWorker != null) {
            modelHealthStaff = (HealthWorker) JsonSerializableAddressBook.obtainPersonFromHashmap(healthWorker);
        } else {
            modelHealthStaff = null;
        }
        if (requestDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RequestDate.class.getSimpleName()));
        }

        final RequestDate modelrequestDate = new RequestDate(this.requestDate);

        if (requestStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RequestStatus.class.getSimpleName()));
        }
        final RequestStatus modelrequestStatus = new RequestStatus(this.requestStatus);

        final Set<Tag> modelConditions = new HashSet<>(requestConditions);
        if (modelHealthStaff == null) {
            return new Request(modelId, modelPatient, modelrequestDate, modelConditions, modelrequestStatus);
        }
        return new Request(modelId, modelPatient, modelHealthStaff, modelrequestDate, modelConditions, modelrequestStatus);
    }

}
