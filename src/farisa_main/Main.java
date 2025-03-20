/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_main;

import farisa_setUp.gameSetUp;

public class Main {
	//

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    	
        gameSetUp game = new gameSetUp("SkyForce Game",500,600);
        game.start();
        
    }
    
}
