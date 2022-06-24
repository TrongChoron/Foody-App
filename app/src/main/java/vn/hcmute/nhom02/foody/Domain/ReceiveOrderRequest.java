package vn.hcmute.nhom02.foody.Domain;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Mon, 6/20/2022
 * Time     : 15:14
 * Filename : ReceiveOrderRequest
 */
public class ReceiveOrderRequest implements OrderRequest{
    private OrderModel orderModel;

    public ReceiveOrderRequest(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
    @Override
    public void sendRequest(String request) {
        System.out.println("Reading Order Request...");
        System.out.println(request);
        String info = this.addOrder();
        System.out.println("Sending Order");
        orderModel.receive(info);
    }
    public String addOrder(){
        return "Adding Order";
    }
}
