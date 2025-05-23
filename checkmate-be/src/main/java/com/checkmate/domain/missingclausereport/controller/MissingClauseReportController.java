package com.checkmate.domain.missingclausereport.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkmate.domain.missingclausereport.dto.response.MissingResponseDto;
import com.checkmate.domain.missingclausereport.service.MissingClauseReportService;
import com.checkmate.domain.user.dto.CustomUserDetails;
import com.checkmate.global.common.response.ApiResult;

@RestController
@RequestMapping("/api/missing")
@RequiredArgsConstructor
@Tag(name = "Missing API", description = "누락 사항 리포트 조회 API")
public class MissingClauseReportController {
	private final MissingClauseReportService missingClauseReportService;

	/**
	 * 각 ai 분석 리포트에 해당되는 누락 사항 리포트 조회
	 *
	 * @param aiAnalysisId ai 분석 리포트 ID
	 * @param userDetails 현재 로그인한 사용자 정보
	 * @return 누락 사항 ID, ai 분석 리포트 ID, 누락 사항 중요도, 누락 사항 내용
	 */
	@Operation(summary = "누락 사항 리포트 조회", description = "누락 사항 리포트를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "누락 사항 리포트 조회 성공")
	})
	@GetMapping("/{aiAnalysisId}")
	@PreAuthorize("isAuthenticated()")
	public ApiResult<List<MissingResponseDto>> getMissingClauseReportByAiAnalysisId(
		@Parameter(description = "AI 분석 리포트 ID", required = true)
		@PathVariable("aiAnalysisId") String aiAnalysisId,
		@Parameter(description = "현재 로그인한 사용자 정보", required = true)
		@AuthenticationPrincipal CustomUserDetails userDetails) {
		List<MissingResponseDto> data =
			missingClauseReportService.getMissingClauseReportByAiAnalysisId(aiAnalysisId, userDetails.getUserId());
		return ApiResult.ok(data);
	}
}
