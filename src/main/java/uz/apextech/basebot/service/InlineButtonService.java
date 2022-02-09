package uz.apextech.basebot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 *  Service for creation {@link InlineKeyboardMarkup} object.
 *  This object represents an inline keyboard that appears right next to the message it belongs to.
 *
 *  Which contains by array of button rows, each represented by an Array of {@link InlineKeyboardButton} objects
 */

public class InlineButtonService {

    public InlineButtonService() {
    }

    public InlineKeyboardMarkup getExampleKeyboard() {
        //  object for return
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //  main component of InlineKeyboardMarkup
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        //  rows of keyboard
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        //  buttons for rows
        InlineKeyboardButton firstBtn = new InlineKeyboardButton();
        //  set properties for firstBtn
        firstBtn.setText("First Btn");
        firstBtn.setUrl("https://t.me/");

        InlineKeyboardButton secondBtn = new InlineKeyboardButton();
        //  set properties for secondBtn
        secondBtn.setText("Second Btn");

        InlineKeyboardButton thirdBtn = new InlineKeyboardButton();
        //  set properties for thirdBtn
        thirdBtn.setText("Third Button Text");

        // add buttons into rows
        firstRow.add(firstBtn);
        firstRow.add(secondBtn);
        secondRow.add(thirdBtn);

        //  add rows into keyboard
        keyboard.add(firstRow);
        keyboard.add(secondRow);

        //  set keyboard for inlineKeyboardMarkup
        inlineKeyboardMarkup.setKeyboard(keyboard);

        /**
         *   Now we have buttons as shown below
         *       __________________________
         *      |      some message       |
         *      |          text           |
         *      |      here               |
         *      |_________________________|
         *      | First Btn .| Second Btn |
         *       --------------------------
         *      |    Third Button Text"   |
         *      ---------------------------
         *
         *
         *              FANTASIZE!
         */
        return inlineKeyboardMarkup;
    }
}
