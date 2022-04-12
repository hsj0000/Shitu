package cn.jsu.Shitu.controller;

import cn.jsu.Shitu.pojo.vo.CommonResult;
import cn.jsu.Shitu.pojo.vo.QuantumVo;
import cn.jsu.Shitu.pojo.vo.UserSignInfoVo;
import cn.jsu.Shitu.service.GuideService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    GuideService GuideService;

    @PostMapping("/siteToRetrieve")
    @ApiOperation("地点检索")
    public CommonResult siteToRetrieve(@RequestBody SiteInfoVo siteInfoVo) {
        return GuideService.siteToRetrieve(siteInfoVo);
    }
    
    @PostMapping("/coordinateTransformation")
    @ApiOperation("坐标转换")
    public CommonResult coordinateTransformation(@RequestBody CoordinateInfoVo coordinateInfoVo) {
        return GuideService.coordinateTransformation(coordinateInfoVo);
    }
    
    @PostMapping("/trajectoryAnalysis")
    @ApiOperation("轨迹分析")
    public CommonResult trajectoryAnalysis(@RequestBody TrajectoryInfoVo trajectoryInfoVo) {
        return GuideService.trajectoryAnalysis(trajectoryInfoVo);
    }
    
    @PostMapping("/BatchCalculateWay")
    @ApiOperation("批量算路")
    public CommonResult BatchCalculateWay(@RequestBody WayInfoVo wayInfoVo) {
        return GuideService.BatchCalculateWay(wayInfoVo);
    }
    
    @PostMapping("/realTimeTrafficQuery")
    @ApiOperation("实时路况查询")
    public CommonResult realTimeTrafficQuery(@RequestBody TrafficInfoVo trafficInfoVo) {
        return GuideService.realTimeTrafficQuery(trafficInfoVo);
    }
}
