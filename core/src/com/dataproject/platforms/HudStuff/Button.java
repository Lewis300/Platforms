package com.dataproject.platforms.HudStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

    private Texture tex;
    private boolean isActive = true;

    private Vector2 buttonPos;
    private Vector2 dimensions;

    public Button(int buttonType)
    {
        if(buttonType == ButtonType.USE){tex = USE_BTN_TEX;}
        else if(buttonType == ButtonType.DONT_USE){tex = DONT_USE_BTN_TEX;}
        else if(buttonType == ButtonType.ROLL){tex = ROLL_BTN_TEX; isActive = false;}

        dimensions = new Vector2(tex.getWidth(), tex.getHeight());
    }

    public Button(int buttonType, Vector2 buttonPos)
    {
        if(buttonType == ButtonType.USE){tex = USE_BTN_TEX;}
        else if(buttonType == ButtonType.DONT_USE){tex = DONT_USE_BTN_TEX;}
        else if(buttonType == ButtonType.ROLL){tex = ROLL_BTN_TEX;}

        dimensions = new Vector2(tex.getWidth(), tex.getHeight());
        this.buttonPos = buttonPos;
    }

    public void render(Batch batch, float dt)
    {
        batch.draw(tex, buttonPos.x, buttonPos.y);
    }

    public void setButtonPosition(Vector2 buttonPos) { this.buttonPos = buttonPos; }

    public boolean isActive(){return isActive;}
    public void setActive (boolean active){isActive = active;}

    public boolean isClicked()
    {

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            if((mouseX > buttonPos.x && mouseX < (buttonPos.x + dimensions.x) ) // if mouse is in the X range of button image
                    && (mouseY > buttonPos.y && mouseY < (buttonPos.y + dimensions.y))) // if mouse is in the Y range of button image
            {
                return true;
            }
        }

        return false;
    }


}
