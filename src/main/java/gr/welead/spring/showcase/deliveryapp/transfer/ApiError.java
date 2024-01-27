package gr.welead.spring.showcase.deliveryapp.transfer;

public record ApiError(Integer status, String message, String path) {
}
