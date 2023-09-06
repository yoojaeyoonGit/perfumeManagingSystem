package perfumeManage.perfumeManagingSystem;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import perfumeManage.perfumeManagingSystem.domain.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Customer customer1 = createMember("유재윤","1105", "Korea", "Seoul", "GwangHwaMoon", "11dong 103ho", "12322");
            em.persist(customer1);

            DiffuserProductRequest diffuserProductRequest1 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff1", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest2 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff2", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest3 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff3", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest1);
            em.persist(diffuserProductRequest2);
            em.persist(diffuserProductRequest3);

            customer1.addDiffuserProductRequest(diffuserProductRequest1);
            customer1.addDiffuserProductRequest(diffuserProductRequest2);
            customer1.addDiffuserProductRequest(diffuserProductRequest3);

            DiffuserProductRequest diffuserProductRequest4 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff4", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest5 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff5", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest6 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff6", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest4);
            em.persist(diffuserProductRequest5);
            em.persist(diffuserProductRequest6);

            customer1.addDiffuserProductRequest(diffuserProductRequest4);
            customer1.addDiffuserProductRequest(diffuserProductRequest5);
            customer1.addDiffuserProductRequest(diffuserProductRequest6);

            diffuserProductRequest4.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest5.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest6.setStatus(ProductionStatus.PROCESSING);

            DiffuserProductRequest diffuserProductRequest7 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff7", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest8 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff8", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest9 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff9", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest7);
            em.persist(diffuserProductRequest8);
            em.persist(diffuserProductRequest9);

            customer1.addDiffuserProductRequest(diffuserProductRequest7);
            customer1.addDiffuserProductRequest(diffuserProductRequest8);
            customer1.addDiffuserProductRequest(diffuserProductRequest9);

            diffuserProductRequest7.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest8.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest9.setStatus(ProductionStatus.COMPLETE);

        }

        public void dbInit2() {
            Customer customer1 = createMember("감성욱","1105", "Korea", "Seoul", "GwangHwaMoon daero", "11dong 103ho", "12322");
            em.persist(customer1);

            DiffuserProductRequest diffuserProductRequest1 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff1", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest2 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff2", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest3 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff3", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest1);
            em.persist(diffuserProductRequest2);
            em.persist(diffuserProductRequest3);

            customer1.addDiffuserProductRequest(diffuserProductRequest1);
            customer1.addDiffuserProductRequest(diffuserProductRequest2);
            customer1.addDiffuserProductRequest(diffuserProductRequest3);

            DiffuserProductRequest diffuserProductRequest4 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff4", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest5 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff5", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest6 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff6", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest4);
            em.persist(diffuserProductRequest5);
            em.persist(diffuserProductRequest6);

            customer1.addDiffuserProductRequest(diffuserProductRequest4);
            customer1.addDiffuserProductRequest(diffuserProductRequest5);
            customer1.addDiffuserProductRequest(diffuserProductRequest6);

            diffuserProductRequest4.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest5.setStatus(ProductionStatus.PROCESSING);
            diffuserProductRequest6.setStatus(ProductionStatus.PROCESSING);

            DiffuserProductRequest diffuserProductRequest7 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff7", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest8 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff8", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");
            DiffuserProductRequest diffuserProductRequest9 = DiffuserProductRequest.createDiffuserProductRequest("hello Diff9", customer1, "Jazz Club 20%, Silver Mountain 50%, No.5 30%", 3500, new Deadline(2025, 11, 4), "https://www.creedfragrances.co.uk/tco-images/unsafe/513x342/filters:upscale():fill(white):quality(75)/https://www.creedfragrances.co.uk/static/uploads/2023/01/feature-2.jpg");

            em.persist(diffuserProductRequest7);
            em.persist(diffuserProductRequest8);
            em.persist(diffuserProductRequest9);

            customer1.addDiffuserProductRequest(diffuserProductRequest7);
            customer1.addDiffuserProductRequest(diffuserProductRequest8);
            customer1.addDiffuserProductRequest(diffuserProductRequest9);

            diffuserProductRequest7.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest8.setStatus(ProductionStatus.COMPLETE);
            diffuserProductRequest9.setStatus(ProductionStatus.COMPLETE);

        }


        private static Customer createMember(String name, String password, String country, String city, String StreetAddress, String detailedAddress, String zipcode) {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPassword(password);
            customer.setAddress(new Address(country, city, StreetAddress, detailedAddress, zipcode));
            return customer;
        }

    }
}
