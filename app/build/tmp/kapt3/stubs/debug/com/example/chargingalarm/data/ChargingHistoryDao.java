package com.example.chargingalarm.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0011\u001a\u00020\tH\'J\u001e\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\'J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lcom/example/chargingalarm/data/ChargingHistoryDao;", "", "deleteHistory", "", "history", "Lcom/example/chargingalarm/data/ChargingHistory;", "(Lcom/example/chargingalarm/data/ChargingHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldHistory", "cutoffDate", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHistoryByEventType", "Lkotlinx/coroutines/flow/Flow;", "", "eventType", "", "getHistorySince", "startDate", "getRecentHistory", "limit", "", "insertHistory", "app_debug"})
@androidx.room.Dao()
public abstract interface ChargingHistoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM charging_history ORDER BY timestamp DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.chargingalarm.data.ChargingHistory>> getRecentHistory(int limit);
    
    @androidx.room.Query(value = "SELECT * FROM charging_history WHERE eventType = :eventType ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.chargingalarm.data.ChargingHistory>> getHistoryByEventType(@org.jetbrains.annotations.NotNull()
    java.lang.String eventType);
    
    @androidx.room.Query(value = "SELECT * FROM charging_history WHERE timestamp >= :startDate ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.chargingalarm.data.ChargingHistory>> getHistorySince(long startDate);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertHistory(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.data.ChargingHistory history, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteHistory(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.data.ChargingHistory history, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM charging_history WHERE timestamp < :cutoffDate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOldHistory(long cutoffDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}