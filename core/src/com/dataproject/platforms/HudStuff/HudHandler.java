package com.dataproject.platforms.HudStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Utilities.ProababilityTools;

public class HudHandler
{
    private PowerupPrompt pupPropmt;
    private Button p1_roll;
    private Button p2_roll;

    public HudHandler()
    {
        pupPropmt = new PowerupPrompt();

        p1_roll = new Button(Button.ButtonType.ROLL);
        p2_roll = new Button(Button.ButtonType.ROLL);

        p1_roll.setActive(true);
        p2_roll.setActive(false);

        p1_roll.setButtonPosition(new Vector2(20, 20));
        p2_roll.setButtonPosition(new Vector2(Platforms.SCREEN_WIDTH - Button.ROLL_BTN_SPRITE.getWidth() - 20, 20));
    }

    public void render(Batch batch, float dt)
    {
        if(pupPropmt.isOpen()){pupPropmt.render(batch, dt);}
        p1_roll.render(batch, dt);
        p2_roll.render(batch, dt);
    }

    public void handleInput(float dt)
    {
        if(!pupPropmt.isOpen() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) //We cant have people rolling while someone is deciding
        {

            if(p1_roll.isActive() && p1_roll.isClicked())
            {
                p1_roll.setActive(false);
                p2_roll.setActive(true);

                Platforms.gameScreen.handleTurn(true);

                pupPropmt.setDecidingPlayer(ProababilityTools.decider);
                pupPropmt.setAffectedPlayer(ProababilityTools.affected);

                pupPropmt.openPrompt();
            }
            else if(p2_roll.isActive() && p2_roll.isClicked())
            {
                p1_roll.setActive(true);
                p2_roll.setActive(false);

                Platforms.gameScreen.handleTurn(false);

                pupPropmt.setDecidingPlayer(ProababilityTools.decider);
                pupPropmt.setAffectedPlayer(ProababilityTools.affected);

                pupPropmt.openPrompt();
            }
        }

        else if(pupPropmt.isOpen()){pupPropmt.handleInput();}
    }
}
