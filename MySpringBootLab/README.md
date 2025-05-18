###[2-4] springboot 와 JPA 활용
*Entity, Repository,RepositoryTest
*Book 과 BookDetail 1:1 연관관계
    *FetchType.Lazy vs FetchType.EAGER
    *@JoinColumn , mappedBy
    *연관관계의 주인 과 종속
    *Owner(BookDetail), Non-Owner(Book)
    *FK(외래키) 가지고 있는 쪽이 주인(owner)이다.
*Service
*DTO(Data Transfer Object)
*Controller