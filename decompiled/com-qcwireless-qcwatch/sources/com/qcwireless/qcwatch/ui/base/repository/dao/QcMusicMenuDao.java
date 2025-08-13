package com.qcwireless.qcwatch.ui.base.repository.dao;

import com.qcwireless.qcwatch.ui.base.repository.entity.SongMenuEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: QcMusicMenuDao.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH'J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMusicMenuDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/BaseDao;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SongMenuEntity;", "deleteMenu", "", "menuId", "", "queryMenuList", "", "address", "", "queryMenusList", "queryMusicMenuByMenuId", "queryMusicMenuByMenuName", "menuName", "updateMenuName", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface QcMusicMenuDao extends BaseDao<SongMenuEntity> {
    void deleteMenu(long menuId);

    List<SongMenuEntity> queryMenuList(String address);

    List<SongMenuEntity> queryMenusList(String address);

    SongMenuEntity queryMusicMenuByMenuId(String address, long menuId);

    SongMenuEntity queryMusicMenuByMenuName(String address, String menuName);

    void updateMenuName(String menuName, long menuId);
}
