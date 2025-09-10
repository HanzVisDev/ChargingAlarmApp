package com.example.chargingalarm.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChargingHistoryDao_Impl implements ChargingHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ChargingHistory> __insertionAdapterOfChargingHistory;

  private final EntityDeletionOrUpdateAdapter<ChargingHistory> __deletionAdapterOfChargingHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldHistory;

  public ChargingHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChargingHistory = new EntityInsertionAdapter<ChargingHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `charging_history` (`id`,`batteryLevel`,`isCharging`,`powerLevel`,`temperature`,`voltage`,`timestamp`,`eventType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChargingHistory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getBatteryLevel());
        final int _tmp = entity.isCharging() ? 1 : 0;
        statement.bindLong(3, _tmp);
        if (entity.getPowerLevel() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getPowerLevel());
        }
        if (entity.getTemperature() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getTemperature());
        }
        if (entity.getVoltage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getVoltage());
        }
        statement.bindLong(7, entity.getTimestamp());
        if (entity.getEventType() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getEventType());
        }
      }
    };
    this.__deletionAdapterOfChargingHistory = new EntityDeletionOrUpdateAdapter<ChargingHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `charging_history` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChargingHistory entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteOldHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM charging_history WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertHistory(final ChargingHistory history,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChargingHistory.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHistory(final ChargingHistory history,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfChargingHistory.handle(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldHistory(final long cutoffDate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldHistory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffDate);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ChargingHistory>> getRecentHistory(final int limit) {
    final String _sql = "SELECT * FROM charging_history ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charging_history"}, new Callable<List<ChargingHistory>>() {
      @Override
      @NonNull
      public List<ChargingHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryLevel");
          final int _cursorIndexOfIsCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "isCharging");
          final int _cursorIndexOfPowerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "powerLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfVoltage = CursorUtil.getColumnIndexOrThrow(_cursor, "voltage");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "eventType");
          final List<ChargingHistory> _result = new ArrayList<ChargingHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChargingHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpBatteryLevel;
            _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
            final boolean _tmpIsCharging;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCharging);
            _tmpIsCharging = _tmp != 0;
            final Integer _tmpPowerLevel;
            if (_cursor.isNull(_cursorIndexOfPowerLevel)) {
              _tmpPowerLevel = null;
            } else {
              _tmpPowerLevel = _cursor.getInt(_cursorIndexOfPowerLevel);
            }
            final Float _tmpTemperature;
            if (_cursor.isNull(_cursorIndexOfTemperature)) {
              _tmpTemperature = null;
            } else {
              _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            }
            final Integer _tmpVoltage;
            if (_cursor.isNull(_cursorIndexOfVoltage)) {
              _tmpVoltage = null;
            } else {
              _tmpVoltage = _cursor.getInt(_cursorIndexOfVoltage);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            _item = new ChargingHistory(_tmpId,_tmpBatteryLevel,_tmpIsCharging,_tmpPowerLevel,_tmpTemperature,_tmpVoltage,_tmpTimestamp,_tmpEventType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ChargingHistory>> getHistoryByEventType(final String eventType) {
    final String _sql = "SELECT * FROM charging_history WHERE eventType = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (eventType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, eventType);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charging_history"}, new Callable<List<ChargingHistory>>() {
      @Override
      @NonNull
      public List<ChargingHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryLevel");
          final int _cursorIndexOfIsCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "isCharging");
          final int _cursorIndexOfPowerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "powerLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfVoltage = CursorUtil.getColumnIndexOrThrow(_cursor, "voltage");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "eventType");
          final List<ChargingHistory> _result = new ArrayList<ChargingHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChargingHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpBatteryLevel;
            _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
            final boolean _tmpIsCharging;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCharging);
            _tmpIsCharging = _tmp != 0;
            final Integer _tmpPowerLevel;
            if (_cursor.isNull(_cursorIndexOfPowerLevel)) {
              _tmpPowerLevel = null;
            } else {
              _tmpPowerLevel = _cursor.getInt(_cursorIndexOfPowerLevel);
            }
            final Float _tmpTemperature;
            if (_cursor.isNull(_cursorIndexOfTemperature)) {
              _tmpTemperature = null;
            } else {
              _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            }
            final Integer _tmpVoltage;
            if (_cursor.isNull(_cursorIndexOfVoltage)) {
              _tmpVoltage = null;
            } else {
              _tmpVoltage = _cursor.getInt(_cursorIndexOfVoltage);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            _item = new ChargingHistory(_tmpId,_tmpBatteryLevel,_tmpIsCharging,_tmpPowerLevel,_tmpTemperature,_tmpVoltage,_tmpTimestamp,_tmpEventType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ChargingHistory>> getHistorySince(final long startDate) {
    final String _sql = "SELECT * FROM charging_history WHERE timestamp >= ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"charging_history"}, new Callable<List<ChargingHistory>>() {
      @Override
      @NonNull
      public List<ChargingHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "batteryLevel");
          final int _cursorIndexOfIsCharging = CursorUtil.getColumnIndexOrThrow(_cursor, "isCharging");
          final int _cursorIndexOfPowerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "powerLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfVoltage = CursorUtil.getColumnIndexOrThrow(_cursor, "voltage");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfEventType = CursorUtil.getColumnIndexOrThrow(_cursor, "eventType");
          final List<ChargingHistory> _result = new ArrayList<ChargingHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChargingHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpBatteryLevel;
            _tmpBatteryLevel = _cursor.getInt(_cursorIndexOfBatteryLevel);
            final boolean _tmpIsCharging;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCharging);
            _tmpIsCharging = _tmp != 0;
            final Integer _tmpPowerLevel;
            if (_cursor.isNull(_cursorIndexOfPowerLevel)) {
              _tmpPowerLevel = null;
            } else {
              _tmpPowerLevel = _cursor.getInt(_cursorIndexOfPowerLevel);
            }
            final Float _tmpTemperature;
            if (_cursor.isNull(_cursorIndexOfTemperature)) {
              _tmpTemperature = null;
            } else {
              _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            }
            final Integer _tmpVoltage;
            if (_cursor.isNull(_cursorIndexOfVoltage)) {
              _tmpVoltage = null;
            } else {
              _tmpVoltage = _cursor.getInt(_cursorIndexOfVoltage);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpEventType;
            if (_cursor.isNull(_cursorIndexOfEventType)) {
              _tmpEventType = null;
            } else {
              _tmpEventType = _cursor.getString(_cursorIndexOfEventType);
            }
            _item = new ChargingHistory(_tmpId,_tmpBatteryLevel,_tmpIsCharging,_tmpPowerLevel,_tmpTemperature,_tmpVoltage,_tmpTimestamp,_tmpEventType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
