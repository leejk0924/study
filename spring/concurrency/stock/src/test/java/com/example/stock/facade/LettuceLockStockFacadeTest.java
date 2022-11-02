package com.example.stock.facade;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LettuceLockStockFacadeTest {

    @Autowired
    private LettuceLockStockFacade lettuceLockStockFacade;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void insert() {
        Stock stock = new Stock(1L, 100L);
        stockRepository.saveAndFlush(stock);
    }

    @AfterEach
    public void after() {
        stockRepository.deleteAll();
    }


    @Test()
    @DisplayName("동시에 100개의 요청")
    public void 동시에_100개의_요청() throws InterruptedException {
        int threadCount = 100;

        // 비동기로 실행하는 작업을 단순화 하여 사용할 수 있게 도와주는 java api
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        // 다른 쓰레드에서 실행중인 작업을 대기할 수 있도록 도와주는 클래스
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                        try {
                            lettuceLockStockFacade.decrease(1L, 1L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            latch.countDown();
                        }
                    }
            );
        }
        latch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();

        // 100 -(1*199) = 0
        assertEquals(0, stock.getQuantity());
    }
}