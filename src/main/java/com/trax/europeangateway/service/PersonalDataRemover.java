package com.trax.europeangateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trax.europeangateway.model.dto.xsd.omega.BuyerSellerDecisionMakerId;
import com.trax.europeangateway.model.dto.xsd.omega.BuyerSellerId;
import com.trax.europeangateway.model.dto.xsd.omega.InvestmentDecisionId;
import com.trax.europeangateway.model.dto.xsd.omega.InvestmentExecutionId;
import com.trax.europeangateway.model.dto.xsd.omega.MifirBuyerDecisionMakerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirBuyerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirSellerDecisionMakerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.MifirSellerDetails;
import com.trax.europeangateway.model.dto.xsd.omega.TransactionPositionReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonalDataRemover {

	public TransactionPositionReport removePIData(TransactionPositionReport transaction) {
		if (transaction == null)
			return null;
		log.info("PersonalDataRemover:remove personal data for customer transaction id {}", transaction.getProcessingDetails().getCustomerTransactionId());
		removeBuyer(transaction);
		removeSeller(transaction);
		removeBuyerDecisionMaker(transaction);
		removeSellerDecisionMaker(transaction);
		removeInvestmentWithInFirm(transaction);
		removeExecutionWithInFirm(transaction);
		return transaction;
	}

	protected TransactionPositionReport removeBuyer(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDetails().isEmpty()) {
			List<MifirBuyerDetails> buyerList = transaction.getMifir().getCounterpartyDetails().getBuyer()
					.getMifirBuyerDetails();
			buyerList.forEach(x -> {
				removeBuyersellerPIData(x.getBuyerId());
				removeMifirBuyerDetails(x);
			});
		}
		return transaction;
	}

	protected TransactionPositionReport removeSeller(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDetails().isEmpty()) {
			List<MifirSellerDetails> sellerList = transaction.getMifir().getCounterpartyDetails().getSeller()
					.getMifirSellerDetails();
			sellerList.forEach(x -> {
				removeBuyersellerPIData(x.getSellerId());
				removeMifirSellerDetails(x);
			});
		}
		return transaction;
	}

	protected TransactionPositionReport removeBuyerDecisionMaker(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer() != null
				&& transaction.getMifir().getCounterpartyDetails().getBuyer()
						.getMifirBuyerDecisionMakerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getBuyer().getMifirBuyerDecisionMakerDetails()
						.isEmpty()) {
			List<MifirBuyerDecisionMakerDetails> buyerDecisionList = transaction.getMifir().getCounterpartyDetails()
					.getBuyer().getMifirBuyerDecisionMakerDetails();
			buyerDecisionList.forEach(x -> {
				removeBuyerSellerDecisionMakerData(x.getBuyerDecisionMakerId());
				removeBuyerDecisionMakerDetailsData(x);
			});
		}
		return transaction;
	}

	protected TransactionPositionReport removeSellerDecisionMaker(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null
				&& transaction.getMifir().getCounterpartyDetails() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller() != null
				&& transaction.getMifir().getCounterpartyDetails().getSeller()
						.getMifirSellerDecisionMakerDetails() != null
				&& !transaction.getMifir().getCounterpartyDetails().getSeller().getMifirSellerDecisionMakerDetails()
						.isEmpty()) {
			List<MifirSellerDecisionMakerDetails> sellerDecisionList = transaction.getMifir().getCounterpartyDetails()
					.getSeller().getMifirSellerDecisionMakerDetails();
			sellerDecisionList.forEach(x -> {
				removeBuyerSellerDecisionMakerData(x.getSellerDecisionMakerId());
				removeSellerDecisionMakerDetailsData(x);
			});
		}
		return transaction;
	}

	protected TransactionPositionReport removeInvestmentWithInFirm(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null && transaction.getMifir().getOtherDetails() != null
				&& transaction.getMifir().getOtherDetails().getInvestmentDecisionId() != null) {
			removeInvestmentDecisionData(transaction.getMifir().getOtherDetails().getInvestmentDecisionId());
		}
		return transaction;
	}

	protected TransactionPositionReport removeExecutionWithInFirm(TransactionPositionReport transaction) {
		if (transaction != null && transaction.getMifir() != null && transaction.getMifir().getOtherDetails() != null
				&& transaction.getMifir().getOtherDetails().getExecutionId() != null) {
			removeInvestmentExecutionData(transaction.getMifir().getOtherDetails().getExecutionId());
		}
		return transaction;
	}

	private void removeBuyersellerPIData(BuyerSellerId buyerSellerId) {
		buyerSellerId.setNATIONALIDCCPT(null);
		buyerSellerId.setNATIONALIDCONCAT(null);
		buyerSellerId.setNATIONALIDDERCON(null);
		buyerSellerId.setNATIONALIDNIDN(null);
	}

	private void removeMifirBuyerDetails(MifirBuyerDetails mifirBuyerDetails) {
		mifirBuyerDetails.setBuyerBirthdate(null);
		mifirBuyerDetails.setBuyerFirstname(null);
		mifirBuyerDetails.setBuyerSurname(null);
		mifirBuyerDetails.setBuyerBranchCountry(null);
	}

	private void removeMifirSellerDetails(MifirSellerDetails mifirSellerDetails) {
		mifirSellerDetails.setSellerBirthdate(null);
		mifirSellerDetails.setSellerFirstname(null);
		mifirSellerDetails.setSellerSurname(null);
		mifirSellerDetails.setSellerBranchCountry(null);
	}

	private void removeBuyerSellerDecisionMakerData(BuyerSellerDecisionMakerId buyerSellerDecisionMakerId) {
		buyerSellerDecisionMakerId.setNATIONALIDCCPT(null);
		buyerSellerDecisionMakerId.setNATIONALIDCONCAT(null);
		buyerSellerDecisionMakerId.setNATIONALIDDERCON(null);
		buyerSellerDecisionMakerId.setNATIONALIDNIDN(null);
	}

	private void removeBuyerDecisionMakerDetailsData(MifirBuyerDecisionMakerDetails mifirBuyerDecisionMakerDetails) {
		mifirBuyerDecisionMakerDetails.setBuyerDecisionBirthdate(null);
		mifirBuyerDecisionMakerDetails.setBuyerDecisionFirstname(null);
		mifirBuyerDecisionMakerDetails.setBuyerDecisionSurname(null);
	}

	private void removeSellerDecisionMakerDetailsData(MifirSellerDecisionMakerDetails mifirSellerDecisionMakerDetails) {
		mifirSellerDecisionMakerDetails.setSellerDecisionBirthdate(null);
		mifirSellerDecisionMakerDetails.setSellerDecisionFirstname(null);
		mifirSellerDecisionMakerDetails.setSellerDecisionSurname(null);
	}

	private void removeInvestmentDecisionData(InvestmentDecisionId investmentDecisionId) {
		investmentDecisionId.setNATIONALIDCCPT(null);
		investmentDecisionId.setNATIONALIDCONCAT(null);
		investmentDecisionId.setNATIONALIDNIDN(null);
	}

	private void removeInvestmentExecutionData(InvestmentExecutionId investmentExecutionId) {
		investmentExecutionId.setNATIONALIDCCPT(null);
		investmentExecutionId.setNATIONALIDCONCAT(null);
		investmentExecutionId.setNATIONALIDNIDN(null);
	}
}
