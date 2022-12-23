package com.example.smarthomebackend.repository;

import com.example.smarthomebackend.model.Device;
import com.example.smarthomebackend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User u set u.devices =?1 where u.id =?2")
    void updateUser(List<Device> devices, int userId);

    public User findUserByMac(String mac);
//    @Modifying(clearAutomatically = true)
//    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
//    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
}
