(function() {
	var c = ebay.oDocument.addConfig(new EbayCategorySelectorFilmstripConfig(
			'Selling.Categorize.Browse.Selector'));
	c.sUrl = 'http://cgi5.ebay.com/ws/eBayISAPI.dll?LoadCategoryTree&mcat=<#1#>&mode=1&depth=1&version=114&cat1=&sid=597506728302&userid=&pass=&mskufilter=0';
	c.sLayerPrefix = 'lyr_';
	c.sCategorySelectorLabel = 'Sub Categories';
	c.sHtmlString = '<table border=0 cellpadding=0 cellspacing=0><tr><td class="idtRgt"><#1#></td><td valign="top"><#2#></td></tr></table>';
	c.sSelectName = 'fcat';
	c.sSelectedHtmlString = '<table border="0" cellspacing="0" class="broCatSel"><tr><td colpsan="2"><img src="http://pics.ebaystatic.com/aw/pics/s.gif" height="6" width="1" alt=" "/></td></tr><tr><td valign="top" width="42"><img src="http://pics.ebaystatic.com/aw/pics/s.gif" height="1" width="10" alt=" "/><a class="acc-hdn" href="javascript:{}">Success</a><img src="http://pics.ebaystatic.com/aw/pics/icon/iconSuccess_32x32.gif" border="0" align="top" alt="Success" /></td><td valign="top" class="vieItmPreBoxCon">You\'ve selected a category. Click <b>Continue</b>.</td></tr></table>';
	c.sForkSign = ' >';
	c.bIsInline = true;
	c.sNotifyElemName = 'catlayer_inpGrp';
	c.sARIAMsgLyr = 'ariabrowse';
	c.sARIAMsg = 'Sub Categories are available. Use tab key to navigate to the sub categories list';
	c.sARIALeafMsg = 'You have selected a category. Click Continue';
	c.bIsMotors = false;
	c.bIsCore = true;
	c.sSecondCategoryMessageElemName = 'secondCategoryMessage_sec';
	c.bBypassCatSelection = false;
})();