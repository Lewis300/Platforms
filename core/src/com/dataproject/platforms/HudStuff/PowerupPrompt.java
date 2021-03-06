package com.dataproject.platforms.HudStuff;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.dataproject.platforms.PlatformStuff.Platform;
import com.dataproject.platforms.Platforms;
import com.dataproject.platforms.Player;
import com.dataproject.platforms.Powerups.Powerup;
import com.dataproject.platforms.Utilities.MiscTools;
import com.dataproject.platforms.Utilities.ProababilityTools;

public class PowerupPrompt
{
    private static final Texture WINDOW_TEX = new Texture("Windows\\WindowPromptBackground.png");//TODO get path

    private boolean isOpen = false;
    private Vector2 position;

    //Buttons
    private Button use, dontUse;
    private Vector2 usePos;
    private Vector2 dontUsePos;

    private Player decider;
    private Player affected;

    public static Sprite currentEmblem;
    public static String currentPupName;
    public static String currentOddsToHarmUser;
    public static String currentOnHarmSelf;
    public static String currentOnHarmOther;

    public PowerupPrompt()
    {
        position = new Vector2(Platforms.SCREEN_WIDTH/2 - WINDOW_TEX.getWidth()/2, Platforms.SCREEN_HEIGHT/2 - WINDOW_TEX.getHeight()/2); // Centers window

        usePos = new Vector2(position.x + WINDOW_TEX.getWidth()/15, position.y + WINDOW_TEX.getHeight()/10 + 5); //This button is on the left side of the window
        dontUsePos = new Vector2(position.x +WINDOW_TEX.getWidth() - WINDOW_TEX.getWidth()/15 - Button.DONT_USE_BTN_TEX.getWidth(), position.y + WINDOW_TEX.getHeight()/10 + 5); //This button is on the right side of the window

        use = new Button(Button.ButtonType.USE, usePos); //true --> means this button is the 'use powerup' button
        dontUse = new Button(Button.ButtonType.DONT_USE, dontUsePos);//false --> means this button is the 'dont use powerup' button

        //TODO Load all other textures/text associated with this window
    }

    public void render(Batch batch, float dt)
    {
        // render textuers
        batch.draw(WINDOW_TEX, position.x, position.y);
        currentEmblem.setPosition(position.x + 400, position.y + 175);
        currentEmblem.draw(batch);

        use.render(batch, dt, isOpen);
        dontUse.render(batch, dt, isOpen);

        MiscTools.font16.draw(batch, ("You rolled "+currentPupName+"!"), position.x +125, position.y + 300);
        MiscTools.font16.draw(batch, ("Odds in favour of harming YOU "+currentOddsToHarmUser), position.x +50, position.y + 275);
        MiscTools.font12.draw(batch, ("If YOU are harmed, you lose: "+currentOnHarmSelf+" platforms"), position.x +50, position.y + 240);
        MiscTools.font12.draw(batch, ("If THE ENEMY is harmed, they lose: "+currentOnHarmOther+" platforms"), position.x +50, position.y + 210);
        MiscTools.font24.draw(batch, "USE?", position.x + WINDOW_TEX.getWidth()/2 - MiscTools.font16.getSpaceWidth()*6, position.y + 100 );


    }

    public void handleInput() //Who's turn is it, who is affected
    {
        if(use.isClicked())
        {
            decider.gettCurrentlyRolledPowerup().use(affected);
            isOpen = false;
        }
        else if(dontUse.isClicked())
        {
            isOpen = false;
        }
    }

    public void setDecidingPlayer(Player whosTurn){decider = whosTurn;}
    public void setAffectedPlayer(Player affected){this.affected = affected;}

    public void openPrompt()
    {
        isOpen = true;
    }

    public boolean isOpen(){return isOpen;}
}
