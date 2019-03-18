package seedu.address.logic.commands.request;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITIONS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.request.Request;
import seedu.address.model.request.RequestDate;
import seedu.address.model.request.RequestStatus;
import seedu.address.model.tag.ConditionTag;
import seedu.address.model.tag.Conditions;
import seedu.address.model.tag.Tag;

/**
 * Edits an order in the request book.
 */
public class EditRequestCommand extends RequestCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = RequestCommand.COMMAND_WORD + " " + COMMAND_WORD
        + ": Edits the details of the order identified "
        + "by the index number used in the displayed request book. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_DATE + "DATE] "
        + "[" + PREFIX_CONDITIONS + "CONDITION]...\n"
        + "Example: " + RequestCommand.COMMAND_WORD + " " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_CONDITIONS + "Physiotherapy "
        + PREFIX_CONDITIONS + "Dialysis";

    public static final String MESSAGE_EDIT_REQUEST_SUCCESS = "Edited Request: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REQUEST = "This request already exists in the "
        + "request book.";

    private final Index index;
    private final EditRequestDescriptor editRequestDescriptor;

    /**
     * @param index of the request in the filtered request list to edit
     * @param editRequestDescriptor details to edit the request with
     */
    public EditRequestCommand(Index index, EditRequestDescriptor editRequestDescriptor) {
        requireAllNonNull(index, editRequestDescriptor);

        this.index = index;
        this.editRequestDescriptor = new EditRequestDescriptor(editRequestDescriptor);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireAllNonNull(model, history);
        List<Request> lastShownList = model.getFilteredRequestList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REQUEST_DISPLAYED_INDEX);
        }

        Request requestToEdit = lastShownList.get(index.getZeroBased());
        Request editedRequest = createEditedRequest(requestToEdit, editRequestDescriptor);

        if (!requestToEdit.isSameRequest(editedRequest) && model.hasRequest(editedRequest)) {
            throw new CommandException(MESSAGE_DUPLICATE_REQUEST);
        }

        model.setRequest(requestToEdit, editedRequest);
        model.updateFilteredRequestList(Model.PREDICATE_SHOW_ALL_REQUESTS);
        model.commitRequestBook();
        return new CommandResult(String.format(MESSAGE_EDIT_REQUEST_SUCCESS, editedRequest));
        // List<Request> lastShownList = model.getF()
    }

    /**
     * Creates and returns a {@code Request} with the details of {@code requestToEdit}
     * edited with {@code editRequestDescriptor}.
     */
    private static Request createEditedRequest(Request requestToEdit,
                                               EditRequestDescriptor editRequestDescriptor) {
        assert requestToEdit != null;

        Name updatedName =
            editRequestDescriptor.getName().orElse(requestToEdit.getPatient().getName());
        Phone updatedPhone =
            editRequestDescriptor.getPhone().orElse(requestToEdit.getPatient().getPhone());
        Address updatedAddress =
            editRequestDescriptor.getAddress().orElse(requestToEdit.getPatient().getAddress());
        RequestDate updatedRequestDate =
            editRequestDescriptor.getDate().orElse(requestToEdit.getRequestDate());
        RequestStatus updatedRequestStatus = requestToEdit.getRequestStatus();
        Set<ConditionTag> updatedConditions =
            editRequestDescriptor.getConditions().orElse(requestToEdit.getConditions().getConditions());
        HealthWorker updatedHealthWorker;
        if (requestToEdit.getHealthStaff().isPresent()) {
            updatedHealthWorker = requestToEdit.getHealthStaff().get();
        } else {
            updatedHealthWorker = null;
        }

        if (updatedHealthWorker == null) {
            return new Request(updatedName, updatedPhone, updatedAddress, updatedRequestDate,
                new Conditions(updatedConditions), updatedRequestStatus);
        } else {
            return new Request(updatedName, updatedPhone, updatedAddress, updatedRequestDate,
                new Conditions(updatedConditions), updatedRequestStatus, updatedHealthWorker);
        }

    }

    /**
     * Stores the details to edit the request with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class EditRequestDescriptor {
        private Name name;
        private Phone phone;
        private Address address;
        private RequestDate requestDate;
        private Set<ConditionTag> conditions;

        public EditRequestDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code request} is used internally.
         */
        public EditRequestDescriptor(EditRequestDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setDate(toCopy.requestDate);
            setConditions(toCopy.conditions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, address, requestDate, conditions);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDate(RequestDate requestDate) {
            this.requestDate = requestDate;
        }

        public Optional<RequestDate> getDate() {
            return Optional.ofNullable(requestDate);
        }

        /**
         * Returns an unmodifiable food set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code conditions} is null
         */
        public Optional<Set<ConditionTag>> getConditions() {
            return (conditions != null) ? Optional.of(Collections.unmodifiableSet(conditions))
                : Optional.empty();
        }

        /**
         * Sets {@code conditions} to this object's {@code conditions}
         * A defensive copy of {@code conditions} is used internally.
         */
        public void setConditions(Set<ConditionTag> conditions) {
            this.conditions = (conditions != null) ? new HashSet<>(conditions) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRequestDescriptor)) {
                return false;
            }

            // state check
            EditRequestDescriptor editRequestDescriptor = (EditRequestDescriptor) other;

            return getName().equals(editRequestDescriptor.getName())
                && getPhone().equals(editRequestDescriptor.getPhone())
                && getAddress().equals(editRequestDescriptor.getAddress())
                && getDate().equals(editRequestDescriptor.getDate())
                && getConditions().equals(editRequestDescriptor.getConditions());
        }
    }
}
