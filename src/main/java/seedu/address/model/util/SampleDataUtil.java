package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Address;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Nric("S9772466D"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("diabetic", "hbp")),
            new Patient(new Name("Toh Boon Hwa"), new Phone("91725543"), new Email("boonhwa@example.com"),
                    new Nric("S8661274A"), new Address("Blk 162 Tampines Street 61, #02-17"),
                    getTagSet("diabetic", "handicapped")),
            new Patient(new Name("Quek Siu Toh"), new Phone("87724331"), new Email("siutoh1978@example.com"),
                    new Nric("S8238951C"), new Address("Blk 20 Ghim Moh Terrace, #01-70"),
                    getTagSet("dailycare", "alone")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePerson : getSamplePatients()) { // not sure if this should be Patient or Person
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) { //TODO: waiting for conditions set
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
