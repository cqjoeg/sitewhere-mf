/**
 * Created by CQ on 2017/3/14.
 *
 * Util function
 */


/**
 * 判断是否为空
 * @param val
 * @returns
 */
function isNull(val) {
    if (val == undefined || val == null || val == "" || val == ''
        || val == "undefined" || val == "null" || val == "NULL") {
        return true;
    }
    return false;
}
