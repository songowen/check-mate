package com.checkmate.domain.clause.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkmate.domain.clause.dto.response.ClauseResponseDto;
import com.checkmate.domain.clause.service.ClauseService;
import com.checkmate.domain.user.dto.CustomUserDetails;
import com.checkmate.global.common.response.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clauses")
@RequiredArgsConstructor
@Tag(name = "Clause API", description = "조항 조회 API")
public class ClauseController {
	private final ClauseService clauseService;

	/**
	 * 계약서 조항 조회 기능
	 *
	 * @param contractId 계약서 ID
	 * @param userDetails 현재 로그인한 사용자 정보
	 * @return 계약서 조항들
	 */
	@Operation(summary = "게약서 조항 조회", description = "게약서 조항 정보를 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "게약서 조항 정보 조회 성공")
	})
	@GetMapping("{contractId}")
	public ApiResult<List<ClauseResponseDto>> getMyContractClauses(
		@Parameter(description = "계약서 ID", required = true) @PathVariable(value = "contractId") int contractId,
		@Parameter(description = "현재 로그인한 사용자 정보", required = true) @AuthenticationPrincipal CustomUserDetails userDetails) {
		List<ClauseResponseDto> data = clauseService.getMyContractClauses(contractId, userDetails.getUserId());
		return ApiResult.ok(data);
	}
}
