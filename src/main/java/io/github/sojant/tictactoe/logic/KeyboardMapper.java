package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sojant on 2017-10-23.
 */
public class KeyboardMapper {

    Map<String,Point> keyMap = new HashMap<String,Point>();

    public KeyboardMapper(){

        keyMap.put("Q", new Point(0,0));
        keyMap.put("W", new Point(0,1));
        keyMap.put("E", new Point(0,2));

        keyMap.put("A", new Point(1,0));
        keyMap.put("S", new Point(1,1));
        keyMap.put("D", new Point(1,2));

        keyMap.put("Z", new Point(2,0));
        keyMap.put("X", new Point(2,1));
        keyMap.put("C", new Point(2,2));

    }

    public Point getPointFromKeyboard(String key){
        if(key!=null) key=key.toUpperCase();
        return keyMap.get(key);
    }

}
