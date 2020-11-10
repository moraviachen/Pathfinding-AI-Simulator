public class Robot {

    public int XCord;
    public int YCord;
    public int direction;
    public int battery;
    public World world;
    public int steps;
    public boolean isLowBattery=false;

    public int[] ChargingStation = new int[2];

    //direction convention:
    //0 for north, 1 for south, 2 for west, 3 for east


    public Robot(int x, int y, int d){
        XCord=x;
        YCord=y;
        direction=d;
        battery=100;
        world=null;
        steps=2;
    }

    public int infSensor(){

        for(int i=1; i<=20; i++){
            if(direction==0){
                if(world.map[XCord][YCord+i]==1){
                    return i;
                }

                if(YCord+i==world.height-1){
                    return -1;
                }
            }else if(direction==1){
                if(world.map[XCord][YCord-i]==1){
                    return i;
                }

                if(YCord-i==0){
                    return -1;
                }
            }else if(direction==2){
                if(world.map[XCord-i][YCord]==1){
                    return i;
                }

                if(XCord-i==0){
                    return -1;
                }
            }else if(direction==3){
                if(world.map[XCord+i][YCord]==1){
                    return i;
                }

                if(XCord+i==world.width-1){
                    return -1;
                }
            }
        }

        return -1;
    }

    public int touchSensor(){
        for(int i=1; i<=5; i++){
            if(direction==0){
                if(world.map[XCord][YCord+i]==1){
                    return i;
                }

                if(YCord+i==world.height-1){
                    return -1;
                }
            }else if(direction==1){
                if(world.map[XCord][YCord-i]==1){
                    return i;
                }

                if(YCord-i==0){
                    return -1;
                }
            }else if(direction==2){
                if(world.map[XCord-i][YCord]==1){
                    return i;
                }

                if(XCord-i==0){
                    return -1;
                }
            }else if(direction==3){
                if(world.map[XCord+i][YCord]==1){
                    return i;
                }

                if(XCord+i==world.width-1){
                    return -1;
                }
            }
        }

        return -1;
    }

    public int chargeSensor(){
        if(XCord==ChargingStation[0] && YCord==ChargingStation[1]){
            return 1;
        }
        return 0;
    }

    public void walk(int n){


        if(direction==0){
            if(YCord+n>world.height-1){
                n=world.height-1-YCord;
                YCord=world.height-1;

            }else{
            YCord+=n;}
        }else if(direction==1){

            if(YCord-n<0){
                n=YCord;
                YCord=0;
            }else{
            YCord-=n;}

        }else if(direction==2){

            if(XCord-n<0){
                n=XCord;
                XCord=0;
            }else{

            XCord-=n;}

        }else if(direction==3){

            if(XCord+n>world.width){
                n=world.width-1-XCord;
                XCord=world.width-1;
            }else{

            XCord+=n;}
        }

        battery-=n;
        world.update_robot(this);

    }

    public void turn(){
        battery--;

        if(direction==0){
            direction=2;
        }else if(direction==1){
            direction=3;
        }else if(direction==2){
            direction=1;
        }else if(direction==3){
            direction=0;
        }

        world.robotDir=direction;

    }




}
