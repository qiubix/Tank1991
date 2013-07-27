package tank1991;

import event.GameAction;
import event.Keyboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.logging.Logger;

import objects.DynamicObject;
import objects.Enemy;
import objects.Player;
import tank1991.Model.GameState;
import util.Observed;
import util.Observer;

public class Controller implements Observer {

    private GameCore gameCore;
    private Model model;
    private View view;
    private Keyboard keyboard;
    private GameAction[] actions;

    //TODO: change logger type
    private static Logger log = Logger.getLogger("debug");

    private enum Keys {
        UP, DOWN, LEFT, RIGHT, PAUSE, EXIT, SHOOT
    }

    public Controller(GameCore gameCore, Model model, View view) {
        this.gameCore = gameCore;
        this.model = model;
        this.view = view;

        createGameActions();
        initKeyboard();

        this.view.addMainMenuListener(new MainMenuListener());
        this.view.addKeyListener(keyboard);

    }

    /**
     * Konfiguracja zdarzen wejsciowych klawiatury. Tworzy obiekty typu
     * GameAction i przypisuje je do klawiszy
     */
    private void createGameActions() { //TODO: Change method name
        actions = new GameAction[Keys.values().length];
        for (int i = 0; i < actions.length; i++) {
            actions[i] = new GameAction();
        }

    }

    private void initKeyboard() {
        keyboard = new Keyboard();
        keyboard.mapToKey(getAction(Keys.UP), KeyEvent.VK_UP);
        keyboard.mapToKey(getAction(Keys.DOWN), KeyEvent.VK_DOWN);
        keyboard.mapToKey(getAction(Keys.LEFT), KeyEvent.VK_LEFT);
        keyboard.mapToKey(getAction(Keys.RIGHT), KeyEvent.VK_RIGHT);
        keyboard.mapToKey(getAction(Keys.PAUSE), KeyEvent.VK_P);
        keyboard.mapToKey(getAction(Keys.EXIT), KeyEvent.VK_ESCAPE);
        keyboard.mapToKey(getAction(Keys.SHOOT), KeyEvent.VK_Z);
    }

    private GameAction getAction(Keys action) {
        return actions[action.ordinal()];
    }

    //TODO: Zmienic nazwe funkcji, rozbic na mniejsze, usunac duplikaty kodu
    public void getEvents() {
        boolean playerIsAlive = !model.getPlayer().isDead();
        if (getAction(Keys.EXIT).isPressed()) {
            //view.getMainMenu().setIsStarted(!model.isGameOver());
            //view.toggleContext(view.getMainMenu());

            if (model.getGameState() == GameState.LEVEL_UP) {
                model.loadNextLevel();
                view.toggleContext(model.getLevel());
            } else if (!playerIsAlive && !model.isGameOver()) {
                model.reloadLevel();
                view.toggleContext(model.getLevel());
            } else {
                view.getMainMenu().setIsStarted(!model.isGameOver());
                view.toggleContext(view.getMainMenu());
            }
            
            keyboard.reset();
        }
        if (getAction(Keys.PAUSE).isPressed()) {

            view.toggleContext(view.getMainMenu());

            keyboard.reset();
        }
        if (!model.isPaused()) {
            movePlayer();
            if(playerIsShooting())
                shootWithPlayer();
        }
    }

    //TODO: Remove duplicates, extract new methods
    private void movePlayer() {
        Player player = model.getPlayer();
        float velocityX = 0;
        float velocityY = 0;

        if (getAction(Keys.LEFT).isPressed()) {
            getAction(Keys.UP).reset();
            getAction(Keys.DOWN).reset();
            if (player.getX() < 0) {
                player.setVelocityX(0);
            } else {
                velocityX -= player.getSpeed();
                if (player.isTurning()) {
                    GamePhysics.alignObject(model.getLevel(), player);
                }
            }
        }
        if (getAction(Keys.RIGHT).isPressed()) {
            getAction(Keys.UP).reset();
            getAction(Keys.DOWN).reset();
            if (player.getX() + player.getWidth() >= view.getScreen().getWidth()) {
                player.setVelocityX(0);
            } else {
                velocityX += player.getSpeed();
                if (player.isTurning()) {
                    GamePhysics.alignObject(model.getLevel(), player);
                }
            }
        }
        if (getAction(Keys.UP).isPressed()) {
            getAction(Keys.LEFT).reset();
            getAction(Keys.RIGHT).reset();
            if (player.getY() < 0) {
                player.setVelocityY(0);
            } else {
                velocityY -= player.getSpeed();
                if (player.isTurning()) {
                    GamePhysics.alignObject(model.getLevel(), player);
                }
            }
        }
        if (getAction(Keys.DOWN).isPressed()) {
            getAction(Keys.LEFT).reset();
            getAction(Keys.RIGHT).reset();
            if (player.getY() + player.getHeight() >= view.getScreen().getHeight()) {
                player.setVelocityY(0);
            } else {
                velocityY += player.getSpeed();
                if (player.isTurning()) {
                    GamePhysics.alignObject(model.getLevel(), player);
                }
            }
        }
        player.setVelocityX(velocityX);
        player.setVelocityY(velocityY);
    }

    //TODO: Refactor returned condition
    private boolean playerIsShooting() {
        Player player = model.getPlayer();
        return getAction(Keys.SHOOT).isPressed() && System.currentTimeMillis() - player.getBullet().getLastShotTime() >= 500 && (!player.isShooting());
    }

    //TODO: change name, get rid of switch statement
    private void shootWithPlayer() {
        Player player = model.getPlayer();
        player.tankShoots(true);
        player.getBullet().setLastShotTime(System.currentTimeMillis());

        switch (player.getDirection()) {
            case DOWN:
                player.getBullet().setVelocityY(player.getBullet().getSpeed());
                player.getBullet().setX(player.getX()+ player.getWidth()/2  - player.getBullet().getWidth()/2);
                player.getBullet().setY(player.getY() + player.getHeight() );
                break;
            case UP:
                player.getBullet().setVelocityY(-player.getBullet().getSpeed());
                player.getBullet().setX(player.getX()+ player.getWidth()/2  - player.getBullet().getWidth()/2);
                player.getBullet().setY(player.getY() - player.getBullet().getWidth());
                break;
            case RIGHT:
                player.getBullet().setVelocityX(player.getBullet().getSpeed());
                player.getBullet().setX(player.getX() + player.getWidth());
                player.getBullet().setY(player.getY()+player.getHeight()/2  - player.getBullet().getHeight()/2);
                break;
            case LEFT:
                player.getBullet().setVelocityX(-player.getBullet().getSpeed());
                player.getBullet().setX(player.getX() - player.getBullet().getWidth());
                player.getBullet().setY(player.getY()+player.getHeight()/2 - player.getBullet().getHeight()/2);
                break;
        }
    }


    //TODO: Devide into smaller methods, break down switch construction
    private void enemiesDecide() {
        if (!model.isPaused()) {
            for (Iterator<DynamicObject> i = model.getLevel().iterator(); i.hasNext();) {
                DynamicObject object = i.next();
                if (object instanceof Enemy) {
                    switch (((Enemy) object).getDecision()) {
                        case TURN_LEFT:
                            turnEnemyLeft(object);
                            break;
                        case TURN_RIGHT:
                            turnEnemyRight(object);
                            break;
                        case TURN_UP:
                            turnEnemyUp(object);
                            break;
                        case TURN_DOWN:
                            turnEnemyDown(object);
                            break;
                        default:
                    }
                }
            }
        }
    }

    private void turnEnemyDown(DynamicObject object) {
        float velocityX = 0;
        float velocityY = 0;

        object.setVelocityX(0);
        if (object.getY() + object.getHeight() >= view.getScreen().getHeight()) {
            object.setVelocityY(0);
        } else {
            velocityY += ((Enemy) object).getSpeed();
            if (((Enemy) object).isTurning()) {
                GamePhysics.alignObject(model.getLevel(), (Enemy) object);
            }
        }
        object.setVelocityX(velocityX);
        object.setVelocityY(velocityY);
    }

    private void turnEnemyUp(DynamicObject object) {
        float velocityX = 0;
        float velocityY = 0;

        object.setVelocityX(0);
        if (object.getY() + object.getHeight() >= view.getScreen().getHeight()) {
            object.setVelocityY(0);
        } else {
            velocityY -= ((Enemy) object).getSpeed();
            if (((Enemy) object).isTurning()) {
                GamePhysics.alignObject(model.getLevel(), (Enemy) object);
            }
        }
        object.setVelocityX(velocityX);
        object.setVelocityY(velocityY);
    }

    private void turnEnemyRight(DynamicObject object) {
        float velocityX = 0;
        float velocityY = 0;

        object.setVelocityY(0);
        if (object.getX() + object.getWidth() >= view.getScreen().getWidth()) {
            object.setVelocityX(0);
        } else {
            velocityX += ((Enemy) object).getSpeed();
            if (((Enemy) object).isTurning()) {
                GamePhysics.alignObject(model.getLevel(), (Enemy) object);
            }
        }
        object.setVelocityX(velocityX);
        object.setVelocityY(velocityY);
    }

    private void turnEnemyLeft(DynamicObject object) {
        float velocityX = 0;
        float velocityY = 0;

        object.setVelocityY(0);
        if (object.getX() < 0) {
            object.setVelocityX(0);
        } else {
            velocityX -= ((Enemy) object).getSpeed();
            if (((Enemy) object).isTurning()) {
                GamePhysics.alignObject(model.getLevel(), (Enemy) object);
            }
        }
        object.setVelocityX(velocityX);
        object.setVelocityY(velocityY);
    }

    @Override
    public void signal(Observed observed) {
        getEvents();
        enemiesDecide();
    }

    class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "resume":
                    resumeGame();
                    break;
                case "new":
                    nemGame();
                    break;
                case "credits": //TODO: add support to "credits" option in main menu
                    break;
                case "exit":
                    exitGame();
                    break;
            }
        }

        private void nemGame() {
            model.startGame();
            view.getMainMenu().setIsStarted(true);
            resumeGame();
        }

        private void resumeGame() {
            view.toggleContext(model.getLevel());
            model.resumeGame();
        }

        private void exitGame() {
            gameCore.stop();
        }
    }
}
