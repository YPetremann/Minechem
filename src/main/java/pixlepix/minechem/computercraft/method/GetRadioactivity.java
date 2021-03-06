package pixlepix.minechem.computercraft.method;

import dan200.computer.api.IComputerAccess;
import dan200.turtle.api.ITurtleAccess;
import net.minecraft.item.ItemStack;
import pixlepix.minechem.api.core.EnumElement;
import pixlepix.minechem.api.core.EnumRadioactivity;
import pixlepix.minechem.api.util.Util;
import pixlepix.minechem.common.items.ItemElement;
import pixlepix.minechem.common.utils.MinechemHelper;
import pixlepix.minechem.computercraft.ICCMethod;

public class GetRadioactivity implements ICCMethod {

    @Override
    public String getMethodName() {
        return "getRadioactivity";
    }

    @Override
    public Object[] call(IComputerAccess computer, ITurtleAccess turtle, Object[] arguments) throws Exception {
        if (arguments.length == 1) {
            return getRadioactivityFromString(arguments[0]);
        } else {
            return getRadioactivityFromSlot(turtle.getSelectedSlot(), turtle);
        }
    }

    private Object[] getRadioactivityFromSlot(int selectedSlot, ITurtleAccess turtle) {
        ItemStack selectedStack = turtle.getSlotContents(selectedSlot);
        if (selectedStack != null && Util.isStackAnElement(selectedStack)) {
            EnumElement element = ItemElement.getElement(selectedStack);
            return getRadioactiveName(element);
        }
        return null;
    }

    private Object[] getRadioactivityFromString(Object object) throws Exception {
        if (object instanceof String) {
            String query = (String) object;
            EnumElement element = EnumElement.valueOf(query);
            if (element != null)
                return getRadioactiveName(element);
        } else {
            throw new Exception("Argument must be type String");
        }
        return null;
    }

    private Object[] getRadioactiveName(EnumElement element) {
        EnumRadioactivity radioactivity = element.radioactivity();
        String radioactiveName = MinechemHelper.getLocalString("element.property." + radioactivity.name());
        return new Object[]{radioactiveName};
    }

}
