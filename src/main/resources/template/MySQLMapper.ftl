<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${content.dao.classPackage}.${content.dao.className}">

	<!-- 数据库中表名 -->
	<sql id="table">
		${content.entity.tableName}
	</sql>

	<!-- 数据库中表的列名 -->
	<sql id="columns">
		<#list content.entity.noIdAttrs as item>
		${item.columnName}<#if item?has_next>,</#if>
		</#list>
	</sql>

	<!-- 条件sql -->
	<sql id="whereSql">
		<where>
			and kdt_id = ${r'#{param.kdtId}'}
		<#if content.entity.noIdAttrs??><#list content.entity.noIdAttrs as item>
			<#if item.columnName != 'kdt_id'>
			<if test="param.${item.field} != null"> and ${item.columnName} = ${r'#{param.'}${item.field}${r'}'}</if>
			</#if>
		</#list></#if>
		</where>
	</sql>


	<sql id="selectSql">
		select
		<include refid="columns" />
		from
		<include refid="table" />
		<include refid="whereSql" />
	</sql>


	<select id="${content.dao.item.selectOne.value}" resultType="${content.DO.classPackage}.${content.DO.className}">
		<include refid="selectSql" />
		limit 1
	</select>


	<select id="${content.dao.item.selectByParam.value}" resultType="${content.DO.classPackage}.${content.DO.className}">
		<include refid="selectSql" />
		<if test="offset != null and pageSize !=null ">
			LIMIT ${r"#{offset}"}, ${r"#{pageSize}"}
		</if>
	</select>


	<select id="${content.dao.item.countByParam.value}" resultType="java.lang.Integer">
		select
		count(*)
		from
		<include refid="table" />
		<include refid="whereSql" />
	</select>


	<insert id="${content.dao.item.insertByBatch.value}" parameterType="ArrayList">
		insert into <include refid="table" />
		(<#list content.entity.noIdAttrs as item>${item.columnName}<#if item?has_next>,</#if></#list>) values
		<foreach collection="list" item="item" index="index"
				 separator=",">
			(<#list content.entity.noIdAttrs as item>${r'#{item.'}${item.field}${r'}'}<#if item?has_next>,</#if></#list>)
		</foreach>
	</insert>


	<update id="${content.dao.item.updateByKdtId.value}">
		update
		<include refid="table" />
		<set>
			<#list content.entity.noIdAttrs as item>
				<#if item.field != 'kdtId' >
				<if test="param.${item.field} != null">${item.columnName} = ${r'#{param.'}${item.field}${r'}'},</if>
				</#if>
			</#list>
		</set>
		where kdt_id = ${r'#{param.kdtId}'}
	</update>


	<delete id="${content.dao.item.deleteByParam.value}">
		delete from ${content.entity.tableName}
		<include refid = "whereSql"/>
	</delete>
</mapper>