package com.kunlong.metadata.service.impl;

import com.kunlong.metadata.dao.MetadataDictMapper;
import com.kunlong.metadata.model.MetadataDict;
import com.kunlong.metadata.model.MetadataDictExample;
import com.kunlong.metadata.model.MetadataField;
import com.kunlong.metadata.service.MetadataDictService;
import com.kunlong.metadata.service.MetadataFieldService;
import com.kunlong.mybatis.KunlongSql;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysmetadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@Service
public class MetadataDictServiceImpl implements MetadataDictService {

    @Autowired
    MetadataDictMapper metadataDictMapper;
    @Autowired
    MetadataFieldService metadataFieldService;

    public long countByExample(MetadataDictExample example) {
        return 0;
    }


    public int deleteByExample(MetadataDictExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer metadataId) {
        return metadataDictMapper.deleteByPrimaryKey(metadataId);


    }

    public int insert(MetadataDict record) {
        metadataDictMapper.insert(record);
        System.err.println(KunlongUtils.toJSONStringPretty(record));
        System.err.println(record.getMetadataId());
        return record.getMetadataId();

    }

    public int insertSelective(MetadataDict record) {
        metadataDictMapper.insertSelective(record);
        System.err.println(KunlongUtils.toJSONStringPretty(record));
        System.err.println(record.getMetadataId());
        //int id = KunlongSql.selectAutoID(sq);
        return record.getMetadataId();

    }

    @Override
    public List<MetadataDict> selectByExample(MetadataDictExample example) {
             return metadataDictMapper.selectByExample(example);

    }

    @Override
    public MetadataDict selectByPrimaryKey(Integer metadataId) {
        return  metadataDictMapper.selectByPrimaryKey(metadataId);

    }

    public int updateByExampleSelective(MetadataDict record, MetadataDictExample example) {
        return 0;
    }

    public int updateByExample(MetadataDict record, MetadataDictExample example) {
        return metadataDictMapper.updateByExample(record, example);


    }

    public int updateByPrimaryKeySelective(MetadataDict record) {
         metadataDictMapper.updateByPrimaryKeySelective(record);
            return record.getMetadataId();


    }

    public int updateByPrimaryKey(MetadataDict record) {
          return metadataDictMapper.updateByPrimaryKey(record);

    }

    public Integer copyMaster(Integer metadataId) {
        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select * from ytb_manager.metadata_dict ");
        sql1.append(" where metadata_id= ").append(metadataId);
        MetadataDict md = KunlongSql.selectOne(sql1, MetadataDict.class);

        md.setMetadataAlias(md.getMetadataAlias() + System.currentTimeMillis());
        md.setMetadataName(md.getMetadataName() + System.currentTimeMillis());
        md.setMetadataId(null);
        Integer id = this.insertSelective(md);

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ytb_manager.metadata_field ");
        sql.append(" where metadata_id= ").append(metadataId);
        md.setMetadataId(metadataId);

        List<MetadataField> lst = KunlongSql.selectList(sql, MetadataField.class);
        for (MetadataField metadataField : lst) {
            metadataField.setFieldId(null);
            metadataField.setMetadataId(id);
            metadataFieldService.insertSelective(metadataField);
        }
        return id;
    }

    //SELECT  *  FROM information_schema.TABLES WHERE  TABLE_SCHEMA='ytb_manager'
    public Boolean checkTableExists(String db, String tbl) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select 1 from information_schema.TABLES");
        sql.append(" where  TABLE_SCHEMA='").append(db).append("'");
        sql.append(" and table_name='").append(tbl).append("'");
        List lst = KunlongSql.selectList(sql);
        return lst.size() > 0;
    }

    public Integer dpMaster(Integer metadataId) {

        StringBuilder sql1 = new StringBuilder();
        sql1.append(" select * from ytb_manager.metadata_dict ");
        sql1.append(" where metadata_id= ").append(metadataId);
        MetadataDict md = KunlongSql.selectOne(sql1, MetadataDict.class);
        if (!checkTableExists(md.getMetadataDb(), md.getMetadataName())) {
            throw new KunlongError(KunlongError.CODE_NOTEXISTS_RECORD, " 表不存在！");
        }

        sql1.delete(0, sql1.length());
        sql1.append(" select 1 from ").append(md.getMetadataDb());
        sql1.append(".").append(md.getMetadataName());
        sql1.append(" limit 1");
        List<Map<String, Object>> lst = KunlongSql.selectList(sql1);
        //有记录不能删除的
        if (lst.size() > 0) {
            return 0;
        }
        sql1.delete(0, sql1.length());
        sql1.append(" drop table ").append(md.getMetadataDb());
        sql1.append(".").append(md.getMetadataName());
        KunlongSql.update(sql1);
        return metadataId;

    }
}
