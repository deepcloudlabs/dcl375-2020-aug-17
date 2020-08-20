/*
    Customer Model
 */
class Customer {
    constructor() {
        // observables
        this.identity = ko.observable("1")
            .extend({
                'required': true,
                'tcKimlikNo': true,
                'message': "You must enter a valid identity no."
            });
        this.email = ko.observable("@")
            .extend({
                'required': true,
                'email': true,
                'message': "You must enter a valid email."
            });
        this.birthYear = ko.observable(1978).extend({
            'required': true
        });
        this.fullname = ko.observable("jack")
            .extend({
                'required': true,
                'minLength': 5
            });
        this.address = ko.observable("istanbul turkey")
            .extend({
                'required': true,
                'minLength': 12
            });
        this.phone = ko.observable("5555555")
            .extend({
                'required': true,
                'minLength': 7,
                'maxLength': 12
            });
        this.photo = ko.observable(AppConfig.NO_IMAGE);
        // bind methods to this
        this.refresh = this.refresh.bind(this);
    }

    valid(){
        for (let prop in this) {
            let o = this[prop];
            if (ko.isObservable(o) &&
                o.hasOwnProperty('rules')){
                if (!o.isValid()) return false;
            }
        }
        return true;
    }

    validate(){
        for (let prop in this) {
            let o = this[prop];
            if (ko.isObservable(o) &&
                o.hasOwnProperty('rules')){
                ko.validation.validateObservable(o);
            }
        }
    }

    refresh(customer) {
        for (let prop in customer) {
            if (this.hasOwnProperty(prop)) {
                let o = this[prop];
                if (ko.isObservable(o)) {
                    o(customer[prop]);
                } else {
                    o = customer[prop];
                }
            }
        }
    }
}
