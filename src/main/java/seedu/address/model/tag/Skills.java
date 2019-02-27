package seedu.address.model.tag;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a set of skills of a HealthWorker object.
 */
public class Skills {

    private Set<Specialisation> skills;

    public Skills() {
        this.skills = new HashSet<>();
    }

    /**
     * Constructs a Skills object from existing {@code skills}
     *
     * @param skills Skills object to construct from
     */
    public Skills(Skills skills) {
        this.skills = new HashSet<>(skills.getSkills());
    }

    /**
     * Constructs a Skills object from existing {@code skills} Set of
     * Specialisations.
     * TODO: Consider using variable argument list.
     * @param skills Set object containing Specialisation to construct from.
     */
    public Skills(Set<Specialisation> skills) {
        this.skills = skills;
    }

    public Set<Specialisation> getSkills() {
        return this.skills;
    }

    /**
     * Checks if a given Specialisation is in the current skills set.
     * @param specialisation specialisation to check for.
     * @return true if Specialisation is in the set, false otherwise
     */
    public boolean contains(Specialisation specialisation) {
        return this.skills.contains(specialisation);
    }

    /**
     * Checks if a given Specialisation is in the current skills set by first
     * converting the string to Specialisation object.
     * @param specialisation string to check for.
     * @return true if Specialisation Enum of the string specialisation is in
     * the set, false otherwise
     */
    public boolean contains(String specialisation) {
        if (!Specialisation.isValidSpecialisation(specialisation)) {
            return false;
        }

        Specialisation specialisationEnum = Specialisation
                .parseString(specialisation);

        return this.skills.contains(specialisationEnum);
    }

    public void addSpecialisation(Specialisation specialisation) {
        this.skills.add(specialisation);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Skills: ");
        for (Specialisation specialisation : this.skills) {
            stringBuilder.append(specialisation.name());
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Skills)) {
            return false;
        }

        return this.skills.equals(((Skills) other).getSkills());
    }
}