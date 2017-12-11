package springapp.web;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springapp.model.Product;
@Service
public class ProductValidator implements Validator {
@Override
public boolean supports(Class<?> clazz) {
return Product.class.isAssignableFrom(clazz);
}
@Override
public void validate(Object target, Errors errors) {
Product product = (Product) target;
ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
"product.name", "Field name is required.");
ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
"product.description", "Field description is required.");
ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type",
"product.type", "Field type is required.");
if (!(product.getPrice() > 0.0)) {
errors.rejectValue("price", "product.price.too.low",
"Price too low");
}
}
}