package com.trax.europeangateway.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.trax.europeangateway.model.dto.EdsRequestDto;
import com.trax.europeangateway.model.dto.PersonalInformation;
import com.trax.europeangateway.model.dto.xsd.omega.MifirBuyerDecisionMakerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirBuyerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirSellerDecisionMakerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirSellerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonalDataExtractor {
	
	public static final String BUYER = ":buyer";
	public static final String SELLER = ":seller";
	public static final String BUYER_DECISION_MAKER = ":buyerDecisionMaker";
	public static final String SELLER_DECISION_MAKER = ":sellerDecisionMaker";
	public static final String INVESTMENT_DECISION = ":investmentDecision";
	public static final String EXECUTION_DECISION = ":executionDecision";
	public static final String INTERNAL = "INTL";
	public static final String CLIENT = "CLNT";
	
	public List<EdsRequestDto> extract(TransactionPositionReport transaction, String subAccntId) {
		if(transaction == null)
			return Collections.emptyList();
		List<EdsRequestDto> edsRequestList = new ArrayList<>();
		log.info("PersonalDataExtractor:Creating Eds request dto for customer transaction id {}", transaction.getProcessingDetails().getCustomerTransactionId());
		edsRequestList.add(processBuyer(transaction, subAccntId));
		edsRequestList.add(processSeller(transaction, subAccntId));
		edsRequestList.add(processBuyerDecisionMaker(transaction, subAccntId));
		edsRequestList.add(processSellerDecisionMaker(transaction, subAccntId));
		edsRequestList.add(processInvestmentWithInFirm(transaction, subAccntId));
		edsRequestList.add(processExecutionWithInFirm(transaction, subAccntId));
		return edsRequestList.isEmpty() ? null : edsRequestList;
	}
	
	protected EdsRequestDto processBuyer(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDetails().isEmpty()) {
			List<MifirBuyerDetails> buyerList = transaction.getMifir().getCounterpartyDetails().getBuyer()
					.getMifirBuyerDetails();
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + BUYER);
			requestDto.setNaturalPersonType(CLIENT);
			requestDto.setPersonalInfo(new ArrayList<>());
			buyerList.forEach(x -> {
				String concat = x.getBuyerId().getNATIONALIDCONCAT();
				String dercon = x.getBuyerId().getNATIONALIDDERCON();
				String natId = x.getBuyerId().getNATIONALIDNIDN();
				String passport = x.getBuyerId().getNATIONALIDCCPT();
				String dob = x.getBuyerBirthdate();
				String firstName = x.getBuyerFirstname();
				String surName = x.getBuyerSurname();
				String cob = x.getBuyerBranchCountry();
				
				if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(dercon) || !StringUtils.isEmpty(natId)
						|| !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(dob) || !StringUtils.isEmpty(firstName)
						|| !StringUtils.isEmpty(surName) || !StringUtils.isEmpty(cob)) {
					
					requestDto.getPersonalInfo()
							.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				}
			});
			if (!requestDto.getPersonalInfo().isEmpty())
				return requestDto;
		}
		return null;
	}
	
	protected EdsRequestDto processSeller(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDetails().isEmpty()) {
			List<MifirSellerDetails> sellerList = transaction.getMifir().getCounterpartyDetails().getSeller()
					.getMifirSellerDetails();
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + SELLER);
			requestDto.setNaturalPersonType(CLIENT);
			requestDto.setPersonalInfo(new ArrayList<>());
			sellerList.forEach(x -> {
				String concat = x.getSellerId().getNATIONALIDCONCAT();
				String dercon = x.getSellerId().getNATIONALIDDERCON();
				String natId = x.getSellerId().getNATIONALIDNIDN();
				String passport = x.getSellerId().getNATIONALIDCCPT();
				String dob = x.getSellerBirthdate();
				String firstName = x.getSellerFirstname();
				String surName = x.getSellerSurname();
				String cob = x.getSellerBranchCountry();
				
				if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(dercon) || !StringUtils.isEmpty(natId)
						|| !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(dob) || !StringUtils.isEmpty(firstName)
						|| !StringUtils.isEmpty(surName) || !StringUtils.isEmpty(cob)) {
					
					requestDto.getPersonalInfo()
							.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				}
			});
			if (!requestDto.getPersonalInfo().isEmpty())
				return requestDto;
		}
		return null;
	}
	
	protected EdsRequestDto processBuyerDecisionMaker(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDecisionMakerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDecisionMakerDetails().isEmpty()) {
			List<MifirBuyerDecisionMakerDetails> buyerDecisionList = transaction.getMifir().getCounterpartyDetails().getBuyer()
					.getMifirBuyerDecisionMakerDetails();
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + BUYER_DECISION_MAKER);
			requestDto.setNaturalPersonType(CLIENT);
			requestDto.setPersonalInfo(new ArrayList<>());
			buyerDecisionList.forEach(x -> {
				String concat = x.getBuyerDecisionMakerId().getNATIONALIDCONCAT();
				String dercon = x.getBuyerDecisionMakerId().getNATIONALIDDERCON();
				String natId = x.getBuyerDecisionMakerId().getNATIONALIDNIDN();
				String passport = x.getBuyerDecisionMakerId().getNATIONALIDCCPT();
				String dob = x.getBuyerDecisionBirthdate();
				String firstName = x.getBuyerDecisionFirstname();
				String surName = x.getBuyerDecisionSurname();
				String cob = null;
				
				if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(dercon) || !StringUtils.isEmpty(natId)
						|| !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(dob) || !StringUtils.isEmpty(firstName)
						|| !StringUtils.isEmpty(surName)) {
					
					requestDto.getPersonalInfo()
							.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				}
			});
			if (!requestDto.getPersonalInfo().isEmpty())
				return requestDto;
		}
		return null;
	}
	
	protected EdsRequestDto processSellerDecisionMaker(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDecisionMakerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDecisionMakerDetails().isEmpty()) {
			List<MifirSellerDecisionMakerDetails> sellerDecisionList = transaction.getMifir().getCounterpartyDetails().getSeller()
					.getMifirSellerDecisionMakerDetails();
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + SELLER_DECISION_MAKER);
			requestDto.setNaturalPersonType(CLIENT);
			requestDto.setPersonalInfo(new ArrayList<>());
			sellerDecisionList.forEach(x -> {
				String concat = x.getSellerDecisionMakerId().getNATIONALIDCONCAT();
				String dercon = x.getSellerDecisionMakerId().getNATIONALIDDERCON();
				String natId = x.getSellerDecisionMakerId().getNATIONALIDNIDN();
				String passport = x.getSellerDecisionMakerId().getNATIONALIDCCPT();
				String dob = x.getSellerDecisionBirthdate();
				String firstName = x.getSellerDecisionFirstname();
				String surName = x.getSellerDecisionSurname();
				String cob = null;
				
				
				if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(dercon) || !StringUtils.isEmpty(natId)
						|| !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(dob) || !StringUtils.isEmpty(firstName)
						|| !StringUtils.isEmpty(surName)) {
					
					requestDto.getPersonalInfo()
							.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				}
			});
			if (!requestDto.getPersonalInfo().isEmpty())
				return requestDto;
		}
		return null;
	}
	
	protected EdsRequestDto processInvestmentWithInFirm(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getOtherDetails() != null
				&& transaction.getMifir().getOtherDetails().getInvestmentDecisionId() != null) {
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + INVESTMENT_DECISION);
			requestDto.setNaturalPersonType(INTERNAL);
			requestDto.setPersonalInfo(new ArrayList<>());
			String concat = transaction.getMifir().getOtherDetails().getInvestmentDecisionId().getNATIONALIDCONCAT();
			String dercon = null;
			String natId = transaction.getMifir().getOtherDetails().getInvestmentDecisionId().getNATIONALIDNIDN();
			String passport = transaction.getMifir().getOtherDetails().getInvestmentDecisionId().getNATIONALIDCCPT();
			String dob = null;
			String firstName = null;
			String surName = null;
			String cob = null;
			
			if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(natId)) {

				requestDto.getPersonalInfo()
						.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				return requestDto;
			}
		}
		return null;
	}
	
	protected EdsRequestDto processExecutionWithInFirm(TransactionPositionReport transaction, String subAccntId) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getOtherDetails() != null
				&& transaction.getMifir().getOtherDetails().getExecutionId() != null) {
			EdsRequestDto requestDto = new EdsRequestDto();
			requestDto.setSubmissionAccountId(subAccntId);
			requestDto.setExecutingLei(transaction.getMifir().getCounterpartyDetails().getExecutingEntityId().getLEI());
			requestDto.setCorrelationId(transaction.getProcessingDetails().getCustomerTransactionId() + EXECUTION_DECISION);
			requestDto.setNaturalPersonType(INTERNAL);
			requestDto.setPersonalInfo(new ArrayList<>());
			String concat = transaction.getMifir().getOtherDetails().getExecutionId().getNATIONALIDCONCAT();
			String dercon = null;
			String natId = transaction.getMifir().getOtherDetails().getExecutionId().getNATIONALIDNIDN();
			String passport = transaction.getMifir().getOtherDetails().getExecutionId().getNATIONALIDCCPT();
			String dob = null;
			String firstName = null;
			String surName = null;
			String cob = null;
			
			if(!StringUtils.isEmpty(concat) || !StringUtils.isEmpty(passport) || !StringUtils.isEmpty(natId)) {
				
				requestDto.getPersonalInfo()
						.add(createPersonalInformation(concat, dercon, natId, passport, dob, firstName, surName, cob));
				return requestDto;
			}
		}
		return null;
	}
	
	protected PersonalInformation createPersonalInformation(String concat, String dercon, String natId, String passport,
			String dob, String firstName, String surName, String cob) {
		PersonalInformation piData = new PersonalInformation();
		piData.setConcat(concat);
		piData.setDercon(dercon);
		piData.setNationalId(natId);
		piData.setPassportNumber(passport);
		piData.setDob(dob);
		piData.setFirstName(firstName);
		piData.setSurName(surName);
		piData.setCountryOfBranch(cob);
		return piData;
	}
}
