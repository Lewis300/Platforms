package com.dataproject.platforms.HudStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;

public class HudHandler
{
    private PowerupPrompt pupPropmt;
    private Button p1_roll;
    private Button p2_roll;

    public HudHandler()
    {
        pupPropmt = new PowerupPrompt();

        p1_roll = new Button(Button.ButtonType.ROLL); //TODO set position
        p2_roll = new Button(Button.ButtonType.ROLL); //TODO set postition
    }

    public void render(Batch batch, float dt)
    {
        if(pupPropmt.isOpen()){pupPropmt.render(batch, dt);}
    }

    public void handleInput(float dt)
    {
        if(pupPropmt.isOpen()){pupPropmt.handleInput();}

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            if(p1_roll.isActive() && p1_roll.isClicked())
            {
                //TODO roll powerups

                pupPropmt.openPrompt();
            }
        }
    }
}
