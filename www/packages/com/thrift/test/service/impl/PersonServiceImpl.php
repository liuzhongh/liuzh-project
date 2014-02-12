<?php
/**
 * Created by PhpStorm.
 * User: liuzh
 * Date: 14-2-11
 * Time: 下午3:11
 */

namespace com\thrift\test\service\impl;


use com\thrift\test\service\PersonServiceIf;

class PersonServiceImpl implements PersonServiceIf {

    public function getValue()
    {
        $val = array(
            'test'=>'it is test'
        );

        return json_encode($val);
    }

    public function getPerson(\com\thrift\test\bo\Person $person)
    {
        $person->name = $person->name.', hello';

        return $person;
    }
}