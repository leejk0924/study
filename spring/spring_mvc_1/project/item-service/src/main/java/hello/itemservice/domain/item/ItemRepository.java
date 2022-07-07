package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    // 여러개의 쓰레드가 동시에 접근할 경우 HashMap 사용하면 안된다. (ConcurrentHashMap 사용할 것)
    private static final Map<Long, Item> store = new HashMap<>();   // static
    // HashMap 과 마찬가지로 long 사용하면 안된다. (AtomicLong 등 사용할 것)
    private static long sequence = 0L;  // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // store 값의 변화를 없게 하기위해 안전하게 ArrayList 에 감싸서 반환
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        // 설계상 해당 클래스만 사용하는 필드값을 가지는 dto 만들기 (update 에서 id 를 사용하지 않기 떄문)
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
