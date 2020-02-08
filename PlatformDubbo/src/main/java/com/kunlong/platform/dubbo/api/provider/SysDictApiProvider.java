package com.kunlong.platform.dubbo.api.provider;

import app.support.query.PageResult;
import com.kunlong.platform.dubbo.api.DTFactory;
import com.kunlong.dubbo.sys.dto.queryParam.SysDictQueryDTO;
import com.kunlong.dubbo.sys.model.SysDictDTO;
import com.kunlong.dubbo.sys.model.SysDictItemDTO;
import com.kunlong.dubbo.sys.service.SysDictApiService;
import com.kunlong.platform.dubbo.api.transformer.SysDictItemTransformer;
import com.kunlong.platform.dubbo.api.transformer.SysDictTransformer;
import com.kunlong.sys.domain.SysDict;
import com.kunlong.sys.domain.SysDictItem;
import com.kunlong.sys.queryParam.SysDictQueryParam;
import com.kunlong.sys.service.SysDictItemService;
import com.kunlong.sys.service.SysDictService;
import com.kunlong.core.util.BeanMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysDictApiProvider implements SysDictApiService {

	@Autowired
	private SysDictService service;
	
	@Autowired
	private SysDictItemService itemService;
	
	private static final SysDictTransformer T = DTFactory.getInstance(SysDictTransformer.class);
	
	@Override
	public PageResult<SysDictDTO> query(SysDictQueryDTO qpDTO) {
		SysDictQueryParam qp = BeanMapper.getInstance().map(qpDTO, SysDictQueryParam.class);
		List<SysDict> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysDictDTO> page = new PageResult<SysDictDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}
	@Override
	public SysDictDTO findWithItemsById(Integer id) {
		SysDict dict = service.findById(id);
		if(dict == null) {
			return null;
		}
		List<SysDictItem> items = this.itemService.findByDictId(id);
		
		SysDictDTO dictDTO = T.produce(dict);
		List<SysDictItemDTO> itemDTOs = DTFactory.getInstance(SysDictItemTransformer.class).produces(items);
		dictDTO.setItems(itemDTOs);
		return dictDTO;
	}

	@Override
	public Integer saveDictItem(SysDictItemDTO item) {
		SysDictItem entity = DTFactory.getInstance(SysDictItemTransformer.class).consume(item);
		if(item.getId() == null) {
			this.itemService.save(entity);
		} else {
			SysDictItem tmp = this.itemService.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.itemService.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public void deleteItem(Integer itemId) {
		this.itemService.deleteById(itemId);
	}
	@Override
	public SysDictDTO findWithItemsByCode(Integer corpId, String code) {
		SysDict tmp = this.service.findByCode(corpId,code);
		return T.produce(tmp);
	}
	@Override
	public SysDictItemDTO findItemById(Integer itemId) {
		SysDictItem item = this.itemService.findById(itemId);
		return DTFactory.getInstance(SysDictItemTransformer.class).produce(item);
	}
	@Override
	public List<SysDictItemDTO> findItemsByIds(List<Integer> itemIds) {
		List<SysDictItem> items = this.itemService.findByIds(itemIds);
		
		return DTFactory.getInstance(SysDictItemTransformer.class).produces(items);
	}

}
