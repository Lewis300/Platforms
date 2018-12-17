package com.dataproject.platforms.HudStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Utilities.MiscTools;

public class Button
{
    //Makes selecting a button type easier
    public class ButtonType
    {
        public static final int USE = 0;
        public static final int DONT_USE = 1;
        public static final int ROLL = 2;
    }

    public static final Texture USE_BTN_TEX = new Texture("Buttons/YesButton.png"); //TODO Get path
    public static final Texture DONT_USE_BTN_TEX = new Texture("Buttons/NoButton.png"); //TODO get path
    public static final Texture ROLL_BTN_TEX = new Texture("Buttons/RollButton.png");//TODO get path)
    public static final Sprite ROLL_BTN_SPRITE = MiscTools.createScaledSprite(ROLL_BTN_TEX, 80 ,40);

    private Texture tex;
    private boolean isActive = true;

    private Vector2 buttonPos;
    private Vector2 dimensions;

    private int type;

    public Button(int buttonType)
    {
        type = buttonType;

        if(buttonType == ButtonType.USE){tex = USE_BTN_TEX;}
        else if(buttonType == ButtonType.DONT_USE){tex = DONT_USE_BTN_TEX;}
        else if(buttonType == ButtonType.ROLL){isActive = true;}

        if(buttonType != ButtonType.ROLL){dimensions = new Vector2(tex.getWidth(), tex.getHeight());}
        else{dimensions = new Vector2(ROLL_BTN_SPRITE.getWidth(), ROLL_BTN_SPRITE.getHeight());}
    }

    public Button(int buttonType, Vector2 buttonPos)
    {
        if(buttonType == ButtonType.USE){tex = USE_BTN_TEX;}
        else if(buttonType == ButtonType.DONT_USE){tex = DONT_USE_BTN_TEX;}
        else if(buttonType == ButtonType.ROLL){isActive = true;}

        if(buttonType != ButtonType.ROLL){dimensions = new Vector2(tex.getWidth(), tex.getHeight());}
        else{dimensions = new Vector2(ROLL_BTN_SPRITE.getWidth(), ROLL_BTN_SPRITE.getHeight());}

        this.buttonPos = buttonPos;
        ROLL_BTN_SPRITE.setPosition(buttonPos.x, buttonPos.y);
    }

    public void render(Batch batch, float dt, boolean choiceWindowOpen)
    {
        if(type == ButtonType.ROLL)
        {
            if(!choiceWindowOpen && isActive)
            {
                ROLL_BTN_SPRITE.setPosition(buttonPos.x, buttonPos.y);
                ROLL_BTN_SPRITE.draw(batch);
            }
        }
        else{batch.draw(tex, buttonPos.x, buttonPos.y);}
    }

    public void setButtonPosition(Vector2 buttonPos)
    {
        this.buttonPos = buttonPos;
        ROLL_BTN_SPRITE.setPosition(buttonPos.x, buttonPos.y);
    }

    public boolean isActive(){return isActive;}
    public void setActive (boolean active){isActive = active;}

    public boolean isClicked()
    {
        int mouseX = Gdx.input.getX();
        int mouseY = Platforms.SCREEN_HEIGHT-Gdx.input.getY();

//        System.out.println(mouseX+" | "+buttonPos.x+" <> "+(buttonPos.x + dimensions.x));
//        System.out.println(mouseY+" | "+buttonPos.y+" <> "+(buttonPos.y + dimensions.y));

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            if((mouseX > buttonPos.x && mouseX < (buttonPos.x + dimensions.x) ) // if mouse is in the X range of button image
                    && (mouseY > buttonPos.y && mouseY < (buttonPos.y + dimensions.y))) // if mouse is in the Y range of button image
            {
                System.out.println("Here");
                return true;
            }
        }

        return false;
    }


}
