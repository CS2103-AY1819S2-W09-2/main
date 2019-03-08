package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.healthworker.Organization;
import seedu.address.model.tag.Skills;
import seedu.address.model.tag.Tag;

/**
 * @author Lookaz
 * Edits the details of an existing HealthWorker object in the addressbook.
 */
public class EditHealthWorkerCommand extends EditCommand implements HealthWorkerCommand {

    public static final String MESSAGE_EDIT_HEALTHWORKER_SUCCESS = "Edited Health Worker: %1$s";

    private final EditHealthWorkerDescriptor editHealthWorkerDescriptor;

    public EditHealthWorkerCommand(Index index, EditHealthWorkerDescriptor editHealthWorkerDescriptor) {
        super(index);
        requireNonNull(editHealthWorkerDescriptor);

        this.editHealthWorkerDescriptor = editHealthWorkerDescriptor;
    }

    @Override
    public void edit(Model model, Object toEdit, Object edited) {
        model.setHealthWorker((HealthWorker) toEdit, (HealthWorker) edited);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        // TODO: create getFilteredHealthWorkerList method. Implement execute.
        return null;
    }

    /**
     * Creates and returns a {@code HealthWorker} with the details of {@code toEdit}
     * edited with {@code editHealthWorkerDescriptor}.
     */
    private static HealthWorker createEditedHealthWorker(HealthWorker toEdit,
                                                         EditHealthWorkerDescriptor editHealthWorkerDescriptor) {
        assert toEdit != null;

        Name updatedName = editHealthWorkerDescriptor.getName().orElse(toEdit.getName());
        Nric updatedNric = editHealthWorkerDescriptor.getNric().orElse(toEdit.getNric());
        Phone updatedPhone = editHealthWorkerDescriptor.getPhone().orElse(toEdit.getPhone());
        Email updatedEmail = editHealthWorkerDescriptor.getEmail().orElse(toEdit.getEmail());
        Address updatedAddress = editHealthWorkerDescriptor.getAddress().orElse(toEdit.getAddress());
        Set<Tag> updatedTags = editHealthWorkerDescriptor.getTags().orElse(toEdit.getTags());
        Organization updatedOrganization = editHealthWorkerDescriptor.getOrganization()
                .orElse(toEdit.getOrganization());
        Skills updatedSkills = editHealthWorkerDescriptor.getSkills().orElse(toEdit.getSkills());

        return new HealthWorker(updatedName, updatedPhone, updatedEmail, updatedNric, updatedAddress, updatedTags,
                updatedOrganization, updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditHealthWorkerCommand)) {
            return false;
        }

        EditHealthWorkerCommand e = (EditHealthWorkerCommand) other;
        return index.equals(e.index) && editHealthWorkerDescriptor.equals(e.editHealthWorkerDescriptor);
    }

    /**
     * Stores the details to edit the HealthWorker with. Each non-empty field value will replace the
     * corresponding field value of the HealthWorker.
     */
    public static class EditHealthWorkerDescriptor extends EditPersonCommand.EditPersonDescriptor {

        private Organization organization;
        private Skills skills;

        public EditHealthWorkerDescriptor() {}

        public EditHealthWorkerDescriptor(EditPersonCommand.EditPersonDescriptor toCopy,
                                          Organization organization, Skills skills) {
            super(toCopy);
            this.organization = organization;
            this.skills = skills;
        }

        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(this.organization, this.skills);
        }

        public void setOrganization(Organization organization) {
            this.organization = organization;
        }

        public Optional<Organization> getOrganization() {
            return Optional.ofNullable(this.organization);
        }

        public void setSkills(Skills skills) {
            this.skills = skills;
        }

        public Optional<Skills> getSkills() {
            return Optional.ofNullable(this.skills);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditHealthWorkerDescriptor)) {
                return false;
            }

            EditHealthWorkerDescriptor e = (EditHealthWorkerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getOrganization().equals(e.getOrganization())
                    && getSkills().equals(getSkills());
        }
    }
}
