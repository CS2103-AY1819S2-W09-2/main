package seedu.address.model.person.patient;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Conditions;
import seedu.address.model.tag.Tag;

/**
 * Represents a Patient class that can handle requests.
 * Guarantees: details are present and not null, and field values are validated.
 */
public class Patient extends Person {

    private Conditions conditions;

    public Patient(Name name, Phone phone, Email email, Nric nric, Address
            address, Set<Tag> tags) {
        super(name, phone, email, nric, address, tags);
        this.conditions = new Conditions();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Nric: ")
                .append(getNric())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }

    /**
     * Returns true if both Patients have the same name, nric, and phone number
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePatient(Patient other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(this.getName())
                && other.getNric().equals(this.getNric())
                && other.getPhone().equals(this.getPhone());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getNric().equals(getNric())
                && otherPatient.getTags().equals(getTags())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNric(), getAddress(), getPhone(),
                getEmail(), getTags());
    }
}
