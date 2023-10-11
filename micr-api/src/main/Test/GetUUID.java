import java.util.UUID;

/**
 * @author 邓和颖
 * @since 2023/10/11 9:46
 */
public class GetUUID {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
