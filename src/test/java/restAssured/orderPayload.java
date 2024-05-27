package restAssured;

public class orderPayload extends mainSalesOrderClass {
    public static String createOrderPayload() {
        return "<Order DocumentType=\"0001\" OrderNo=\"100100100\" EnterpriseCode=\"Matrix\" >\n" +
                "<OrderLines>\n" +
                "<OrderLine OrderedQty=\"1\" >\n" +
                "<Item ItemID=\"100099\" UnitCost=\"10.0\" UnitOfMeasure=\"EACH\"/>\n" +
                "</OrderLine>\n" +
                "</OrderLines>\n" +
                "<PersonInfoShipTo AddressLine1=\"234 Copley Place\" City=\"Boston\" Country=\"US\" DayPhone=\"\" EMailID=\"\" " +
                "FirstName=\"Goutham\" LastName=\"Sheregar\" MobilePhone=\"\" State=\"MA\" " +
                "ZipCode=\"02116\"/>\n" +
                "<PersonInfoBillTo AddressLine1=\"234 Copley Place\" " +
                "City=\"Boston\" Country=\"US\" DayPhone=\"\" EMailID=\"\" " +
                "FirstName=\"Lakshmi\" LastName=\"A\" MobilePhone=\"\" State=\"MA\" " +
                "ZipCode=\"02116\"/>\n" +
                "</Order>";
    }

    public static String resolveHolds(String orderNo) {
        return "<Order Action=\"MODIFY\" EnterpriseCode=\"Matrix\" OrderNo=\"" + orderNo + "\">\n" +
                "    <OrderHoldTypes>\n" +
                "        <OrderHoldType HoldType=\"YCD_DUPLICATE_ORDER\" ReasonText=\"ABCD\" Status=\"1300\"/>\n" +
                "        <OrderHoldType HoldType=\"YCD_FRAUD_CHECK\" ReasonText=\"NO\" Status=\"1300\"/>\n" +
                "    </OrderHoldTypes>\n" +
                "</Order>";
    }

    public static String scheduleOrder(String orderHeaderKey) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ScheduleOrder OrderHeaderKey=\"" + orderHeaderKey + "\"/>";
    }

    public static String createShipment(String orderNo) {
        return "<Shipment ActualFreightCharge=\"35.12\" ActualShipmentDate=\"2024-04-23T04:08:32+00:00\" SellerOrganizationCode=\"Matrix\" CarrierServiceCode=\"2nd Day Air\" DocumentType=\"0001\" EnterpriseCode=\"Matrix\" SCAC=\"UPSN\" ShipNode=\"STORE_VENDOR01\" TotalWeight=\"5.00\">\n" +
                "    <Containers>\n" +
                "        <Container ContainerGrossWeight=\"15.100000\" ContainerGrossWeightUOM=\"KG\" ContainerHeight=\"300\" ContainerHeightUOM=\"MM\" ContainerLength=\"300\" ContainerLengthUOM=\"MM\" ContainerNo=\"Container_20210609_01\" ContainerSeqNo=\"2\" ContainerType=\"PARCEL\" ContainerWidth=\"300\" ContainerWidthUOM=\"MM\" ActualWei77ght=\"\" TrackingNo=\"Z20210609001\">\n" +
                "            <ContainerDetails>\n" +
                "                <ContainerDetail Quantity=\"1.00\">\n" +
                "                    <ShipmentLine DocumentType=\"0001\" EnterpriseCode=\"Matrix\" ItemID=\"100099\" OrderNo=\"" + orderNo + "\" PrimeLineNo=\"1\" ProductClass=\"GOOD\" Quantity=\"1.00\" ReleaseNo=\"1\" SubLineNo=\"1\" UnitOfMeasure=\"EACH\">\n" +
                "\t\t\t\t\t</ShipmentLine>\n" +
                "                </ContainerDetail>\n" +
                "            </ContainerDetails>\n" +
                "            <Extn ExtnTrackingURL=\"\" ExtnCarrierName=\"\"/>\n" +
                "        </Container>\n" +
                "    </Containers>\n" +
                "    <ShipmentLines>\n" +
                "        <ShipmentLine DocumentType=\"0001\" EnterpriseCode=\"Matrix\" ItemID=\"100099\" OrderNo=\"" + orderNo + "\" PrimeLineNo=\"1\" ProductClass=\"GOOD\" Quantity=\"1.00\" ReleaseNo=\"1\" SubLineNo=\"1\" UnitOfMeasure=\"EACH\">\n" +
                "        </ShipmentLine>\n" +
                "    </ShipmentLines>\n" +
                "</Shipment>";
    }

    public static String confirmShipment(String shipmentNo, String shipmentKey) {
        /*return "<Shipment ActualFreightCharge=\"35.12\" ActualShipmentDate=\"2024-04-23T04:08:32+00:00\" SellerOrganizationCode=\"Matrix\" CarrierServiceCode=\"2nd Day Air\" DocumentType=\"0001\" EnterpriseCode=\"Matrix\" SCAC=\"UPSN\" ShipNode=\"STORE_VENDOR01\" TotalWeight=\"5.00\">\n" +
                "    <Containers>\n" +
                "        <Container ContainerGrossWeight=\"15.100000\" ContainerGrossWeightUOM=\"KG\" ContainerHeight=\"300\" ContainerHeightUOM=\"MM\" ContainerLength=\"300\" ContainerLengthUOM=\"MM\" ContainerNo=\"Container_20210609_01\" ContainerSeqNo=\"2\" ContainerType=\"PARCEL\" ContainerWidth=\"300\" ContainerWidthUOM=\"MM\" ActualWei77ght=\"\" TrackingNo=\"Z20210609001\">\n" +
                "            <ContainerDetails>\n" +
                "                <ContainerDetail Quantity=\"1.00\">\n" +
                "                    <ShipmentLine DocumentType=\"0001\" EnterpriseCode=\"Matrix\" ItemID=\"100099\" OrderNo=\"" + orderNo + "\" PrimeLineNo=\"1\" ProductClass=\"GOOD\" Quantity=\"1.00\" ReleaseNo=\"1\" SubLineNo=\"1\" UnitOfMeasure=\"EACH\">\n" +
                "\n" +
                "        </ShipmentLine>\n" +
                "                </ContainerDetail>\n" +
                "            </ContainerDetails>\n" +
                "            <Extn ExtnTrackingURL=\"\" ExtnCarrierName=\"\"/>\n" +
                "        </Container>\n" +
                "    </Containers>\n" +
                "    <ShipmentLines>\n" +
                "        <ShipmentLine DocumentType=\"0001\" EnterpriseCode=\"Matrix\" ItemID=\"100099\" OrderNo=\"" + orderNo + "\" PrimeLineNo=\"1\" ProductClass=\"GOOD\" Quantity=\"2.00\" ReleaseNo=\"1\" SubLineNo=\"1\" UnitOfMeasure=\"EACH\">\n" +
                "        </ShipmentLine>\n" +
                "    </ShipmentLines>\n" +
                "</Shipment>";*/
        return "<Shipment SellerOrganizationCode=\"Matrix\" ShipNode=\"Matrix_WH1\" ShipmentKey=\""+shipmentKey+"\" ShipmentNo=\""+shipmentNo+"\"/>";
    }

    public static String releaseOrder(String orderHeaderKey, String orderNo) {
        return "<ReleaseOrder CheckInventory=\"Y\" DocumentType=\"0001\" EnterpriseCode=\"Matrix\"  IgnoreTransactionDependencies=\"Y\" OrderHeaderKey=\"" + orderHeaderKey + "\" OrderNo=\"" + orderNo + "\"/>";
    }
}
