package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

    final private StockRepository stockRepository;
    // synchronized 를 해도 Transactional 로 인해 레이스 컨디션 문제가 해결되지 않는다.
    // 왜냐하면 해당 메서드는 동기화처리 해도 Transactional 이 끝나기 전에 다른 쓰레드가 해당 메서드에 접근이 가능하여 정상적인 테스트가 진행되지 않는다.
    // Transactional 을 지우면 해당 테스트는 정상적으로 작동한다.
    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // get stock
        Stock stock = stockRepository.findById(id).orElseThrow();
        // 재고 감소
        stock.decrease(quantity);
        // 저장
        stockRepository.saveAndFlush(stock);
    }
}
