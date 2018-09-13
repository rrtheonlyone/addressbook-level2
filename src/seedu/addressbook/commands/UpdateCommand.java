package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.Name;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 John";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Updated Person: %1$s";

    public Name name;


    public UpdateCommand(int targetVisibleIndex, String name) throws IllegalValueException {
        super(targetVisibleIndex);
        this.name = new Name(name);
    }


    @Override
    public CommandResult execute()  {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.updatePerson(target, name);
            return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }
}
