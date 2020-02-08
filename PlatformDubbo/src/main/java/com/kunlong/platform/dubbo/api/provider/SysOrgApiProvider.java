package com.kunlong.platform.dubbo.api.provider;

import app.support.query.PageResult;
import app.support.tree.TreeNode;
import com.kunlong.platform.dubbo.api.DTFactory;
import com.kunlong.dubbo.sys.dto.queryParam.SysOrgQueryDTO;
import com.kunlong.dubbo.sys.model.SysOrgDTO;
import com.kunlong.dubbo.sys.service.SysOrgApiService;
import com.kunlong.platform.dubbo.api.transformer.SysOrgTransformer;
import com.kunlong.sys.domain.SysOrg;
import com.kunlong.sys.service.SysOrgService;
import com.kunlong.core.util.BeanMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysOrgApiProvider implements SysOrgApiService {

	@Autowired
	private SysOrgService service;
	
	private static final SysOrgTransformer T = DTFactory.getInstance(SysOrgTransformer.class);
	
	@Override
	public PageResult<SysOrgDTO> query(SysOrgQueryDTO qpDTO) {
		com.kunlong.sys.domain.SysOrg.QueryParam qp = BeanMapper.getInstance().map(qpDTO, com.kunlong.sys.domain.SysOrg.QueryParam.class);
		List<SysOrg> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysOrgDTO> page = new PageResult<SysOrgDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysOrgDTO entityDTO) {
		SysOrg entity = T.consume(entityDTO);
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysOrg tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysOrgDTO findById(Integer id) {
		SysOrg tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public void delete(Integer id) {
		this.service.deleteById(id);
	}

	@Override
	public List<SysOrgDTO> findByIds(List<Integer> ids) {
		List<SysOrg> suList = this.service.findByIds(ids);
		return T.produces(suList);
	}

	@Override
	public TreeNode<SysOrgDTO> tree(Integer corpId) {
		com.kunlong.core.support.tree.TreeNode<SysOrg> root = this.service.getRootNode(corpId);
		
		return transTree(root);
	}
	
	private TreeNode<SysOrgDTO> transTree(com.kunlong.core.support.tree.TreeNode<SysOrg> node){
		TreeNode<SysOrgDTO> r = new TreeNode<SysOrgDTO>();
		r.setId(node.getId());
		r.setNodeAttr(node.getNodeAttr());
		r.setNodeVal(DTFactory.getInstance(SysOrgTransformer.class).produce(node.getNodeVal()));
		r.setParentId(node.getParentId());
		r.setText(node.getText());
		
		if(node.getChildren() != null ) {
			r.setChildren(new ArrayList<TreeNode<SysOrgDTO>>());
			
			for(com.kunlong.core.support.tree.TreeNode<SysOrg> cNode:node.getChildren()) {
				r.getChildren().add(this.transTree(cNode));
			}
		}
		return r;
	}
	
}
