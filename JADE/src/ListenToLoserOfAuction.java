public class ListenToLoserOfAuction extends CB {
    @Override
    public void action() {
        this.listenTo(Mediator.LOSEROFAUCTION, new Message(){
            public void execute(Object object){
                AuctionNotification an = (AuctionNotification) object;
                say("Yay, I just won an auction: " + an.getAuction());
            }
        });
    }
}
